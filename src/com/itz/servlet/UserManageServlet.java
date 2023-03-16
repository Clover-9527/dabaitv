package com.itz.servlet;

import com.itz.dao.UserDao;
import com.itz.dao.impl.UserDaoImpl;
import com.itz.entity.PageBean;
import com.itz.entity.User;
import com.itz.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.UUID;

@MultipartConfig(location="D:\\",fileSizeThreshold=1024)
@WebServlet(name = "UserManageServlet", value = "/userManage")
public class UserManageServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取客户端指定参数operator
        String operator =request.getParameter("operator");
        Class clazz =this.getClass();
        //获取执行的放法
        try {
            Method method  = clazz.getMethod(operator, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检测用户名是否存在
    public void findName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数名称
        String username = request.getParameter("username");
        UserDao dao = new UserDaoImpl();
        User user = new User();
        user = service.findUser(username);
        if(user == null){
            response.getWriter().write("1");
        } else{
            response.getWriter().write("2");
        }
    }

    //检测手机号是否被绑定
    public void queryPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        boolean b=service.queryPhone(phone);
        if(b){
            response.getWriter().write("1");
        } else{
            response.getWriter().write("2");
        }
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单提交的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        // 1.1 获取用户填写的验证码
        String code_form = request.getParameter("code_form");
        // 1.2 获取session中保存的验证码
        String code_session = (String) request.getSession().getAttribute("code_session");
        // 1.3 判断
        // 如果session中验证码为空 或 session中验证码和用户填写的验证码不一致, 就不合法,提示错误
        if (code_session == null || !code_session.equalsIgnoreCase(code_form)) {
            request.setAttribute("errorMsg", "验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
              return;
        }
        User user1 = service.findUser(username);
        if(user1==null){
            request.setAttribute("loginError", "用户名不存在!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 2 获取参数 封装到entity类中
        User user = new User(username,password);
        // 3 调用业务层 处理登录业务
        User loginUser = new User();
        loginUser = service.loginCheck(user); // 业务层返回给web层
        // 4 判断是否登录成功
        if (loginUser != null) {
            // 用户名和密码正确，若勾选了记住密码，则创建session对象，并返回
            String pwdRem = request.getParameter("pwdremember");

            // 用户名和密码正确，若勾选了自动登录，则创建Cookie对象，并返回
            String autologin = request.getParameter("autologin");

            if(pwdRem!=null) {
                // 创建Cookie对象
                Cookie pwdInfo = new Cookie("pwdRem", username+":"+password);
                // 设置Cookie的生存时间
                pwdInfo.setMaxAge(60 * 60 * 24);
                // 添加Cookie
                response.addCookie(pwdInfo);
            }else{
                Cookie pwdInfo = new Cookie("pwdRem", "");
                pwdInfo.setMaxAge(0);
                response.addCookie(pwdInfo);
            }

            if(autologin!=null) {
                // 创建Cookie对象
                Cookie loginInfo = new Cookie("loginInfo", username+":"+password);
                // 设置Cookie的生存时间
                loginInfo.setMaxAge(60 * 60 * 24);
                // 添加Cookie
                response.addCookie(loginInfo);
            }
            //转跳管理页
            if(username.equals("admin")) {
                response.sendRedirect(request.getContextPath() +"/admin/index.jsp");
                return;
            }
            session.setAttribute("user",loginUser);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } else {
            // 登录失败，将错误信息保存到request中
            request.setAttribute("loginError", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    //注销
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            // 在session中移除保存的用户信息
            request.getSession().removeAttribute("user");
            // 在cookie中将自动登录的用户删除 -- 重新创建名为loginInfo的cookie
            Cookie cookie = new Cookie("loginInfo", "");
            cookie.setMaxAge(0);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            // 跳转页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1 获取参数 封装到entity类中
        String uid = UUID.randomUUID().toString();
        String nickname=request.getParameter("nickname");
        String username =request.getParameter("username");
        String password=request.getParameter("password");
        String pwd=request.getParameter("pwd");
        String phone = request.getParameter("phone");

        if( nickname.trim().equals("")||username.trim().equals("")||password.trim().equals("")|| (!password.equals(pwd))|| (phone.trim().equals(""))  ) {
            response.sendRedirect("/dabaitv/register.jsp");
            return;
        }

        // 获取商品图片
        Part part = request.getPart("uimg");
        // 得到web中存储图片的目录 productImg
        String path = request.getServletContext().getRealPath("/userImg");

        File file = new File(path);
        if(! file.exists()){
            file.mkdirs();
        }
        // 获取图片的具体内容
        String header = part.getHeader("content-disposition");
        // 得到上传图片文件名
        String fname = header.substring(header.lastIndexOf("=")+2,header.length()-1);
        String uimg = "/userImg/"+ uid + "-" + fname; // 生成保存到数据表的路径
        // 上传文件
        part.write(path+"/"+uid+"-"+fname);
        User user = new User(uimg,username,password,nickname,phone);

        // 2 调用业务层 添加用户
        boolean b= service.addUser(user);
        if(b) {
            System.out.println("注册成功!");
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }else {
            System.out.println("注册失败!");
            response.sendRedirect(request.getContextPath()+"/register.jsp");
        }
    }

    //刷新验证码
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 需求: 生成验证码
        // 1 生成一个画布对象
        int width = 120;
        int height = 40;
        BufferedImage bufi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 3 从画布上获取画笔
        Graphics g = bufi.getGraphics();

        // 4 填充背景色: 白色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        // 5 绘制红色边框
        g.setColor(Color.red);
        g.drawRect(0, 0, width -1 , height - 1);

        // 6 在画布上 随机产生4个字符
        // 6.1 准备数据
        String data = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
        // 6.2 随机对象
        Random r = new Random();
        // 7.1 准备一个变量保存 验证码的值
        String code = "";

        // 6.3 循环产生4个
        for (int i = 0; i < 4; i++) {
            // 6.3.3 设置随机颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

            // 6.3.2 设置字体
            g.setFont(new Font("楷体", Font.BOLD, 30));

            // 6.3.1 绘制字符
            // 产生随机索引
            int index = r.nextInt(data.length());
            char c = data.charAt(index);

            // 7.2 将生成的验证码字符 追加到 验证码变量中
            code += c + "";

            // 绘制字符串
            g.drawString(c + "", 10 + i * 30, 30);
        }

        // 7 将生成的验证码 打印到控制台
        System.out.println(code);

        // 8 绘制干扰线
        for (int i = 0; i < 10; i++) {
            // 设置随机颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }

        // 9 将生成的验证码保存到session中
        request.getSession().setAttribute("code_session", code);
        // 2 向画布内容输出给浏览器
        ImageIO.write(bufi, "jpg", response.getOutputStream());
    }

    //验证码检查
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1.1 获取用户填写的验证码
        String code_form = request.getParameter("code_form");
        // 1.2 获取session中保存的验证码
        String code_session = (String) request.getSession().getAttribute("code_session");
        // 1.3 判断
        // 如果session中验证码为空 或 session中验证码和用户填写的验证码不一致, 就不合法,提示错误
        if (code_session == null || !code_session.equalsIgnoreCase(code_form)) {
            request.setAttribute("errorMsg", "验证码错误!");
            response.getWriter().write("2");
        }else {
            response.getWriter().write("1");
        }
    }

    //登录检查
    public void loginCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        // 1. 获取浏览器携带的cookie
        Cookie[] cookies = request.getCookies();
        String value1 = null; // 记住密码
        String value2 = null; // 自动登录
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("pwdRem".equals(name)) {
                    value1 = cookie.getValue(); // username:password
                }
                if ("loginInfo".equals(name)) {
                    value2 = cookie.getValue(); // username:password
                    break;
                }
            }
            //记住密码ck
            if (value1 != null &&value1 != "") {// 获取用户名和密码：username-password
                String[] parts = value1.split(":"); // username,password
                String username = parts[0];
                String password = parts[1];
                // 验证
                User pwdInfo = new User(username, password);
                session.setAttribute("pwdInfo", pwdInfo);
            }else{
                session.removeAttribute("pwdInfo");
            }
            //自动登录ck
            if (value2 != null) {// 获取用户名和密码：username-password
                String[] parts = value2.split(":"); // username,password
                String username = parts[0];
                String password = parts[1];
                // 验证
                User user = new User(username, password);
                User loginUser = new User();

                loginUser = service.loginCheck(user); // 业务层返回给web层
                session.setAttribute("user", loginUser);
            }
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

    //修改用户信息
    public void alterUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int uid=user.getUid();
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");

        if( phone.trim().equals("")){
            request.setAttribute("phoneCheck","请输入手机号!");
            request.getRequestDispatcher("userInfo.jsp").forward(request,response);
        }else if( !user.getPassword().equals(password) ){
            request.setAttribute("passwordCheck","密码错误!");
            request.getRequestDispatcher("userInfo.jsp").forward(request,response);
        }else if( newPassword.trim().equals("")) {
            request.setAttribute("pwd1Check","请输入密码!");
            request.getRequestDispatcher("userInfo.jsp").forward(request,response);
        }else if( !newPassword.equals(newPassword2)){
            request.setAttribute("pwd2Check","两次密码不同!");
            request.getRequestDispatcher("userInfo.jsp").forward(request,response);
        }

        // 获取用户头像
        Part part = request.getPart("uimg");
        // 得到web中存储图片的目录 productImg
        String path = this.getServletContext().getRealPath("/userImg");
        File file = new File(path);
        if(! file.exists()){
            file.mkdirs();
        }
        // 获取图片的具体内容
        String header = part.getHeader("content-disposition");
        // 得到上传图片文件名
        String fname = header.substring(header.lastIndexOf("=")+2,header.length()-1);
        String uimg = "/userImg/"+ uid + "-" + fname; // 生成保存到数据表的路径
        // 上传文件
        part.write(path+"/"+uid+"-"+fname);
        User newUser = new User(uimg,username,newPassword,nickname,phone);

        newUser.setUid(uid);
        boolean b=service.alterUser(newUser);
        newUser = service.findUser(newUser.getUsername());
        request.getSession().setAttribute("user",newUser);
        if(b){
             logout(request,response);
        } else
            response.sendRedirect("userInfo.jsp");
    }

    //删除用户
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid=request.getParameter("uid");
        boolean b = service.deleteUser(uid);
        queryByPage(request,response);
    }

    //通过id查找用户信息，回显数据
    public void findByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
       User user = service.findUser(username);
        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher("/admin/updateUser.jsp").forward(request,response);
    }

    //分页查询
    public void queryByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取客户端提交上来的当前页数
        String currentPage = request.getParameter("currentPage");
        //判空
        if (currentPage == null){
            currentPage = "1";
        }
        //定义每页显示的条数
        int pageSize = 10;
        //调用业务层的方法,传递 当前页数和每页显示条数
        PageBean<User> userPageBean = service.queryByPage(Integer.parseInt(currentPage), pageSize);
        //对象存储在域中
        request.setAttribute("userPageBean",userPageBean);
        //转发
        request.getRequestDispatcher("/admin/userManage.jsp").forward(request,response);
    }

    //修改用户信息(后台)
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int uid=user.getUid();
        String nickname = request.getParameter("nickname");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");

        // 获取用户头像
        Part part = request.getPart("uimg");
        // 得到web中存储图片的目录 productImg
        String path = this.getServletContext().getRealPath("/userImg");
        File file = new File(path);
        if(! file.exists()){
            file.mkdirs();
        }
        // 获取图片的具体内容
        String header = part.getHeader("content-disposition");
        // 得到上传图片文件名
        String fname = header.substring(header.lastIndexOf("=")+2,header.length()-1);
        String uimg = "/userImg/"+ uid + "-" + fname; // 生成保存到数据表的路径
        // 上传文件
        part.write(path+"/"+uid+"-"+fname);


        Object[] params={nickname,username,phone,uimg,uid};
        boolean b=service.updateUser(params);
            response.sendRedirect(request.getContextPath()+"/userManage?operator=queryByPage");
    }

    //重置密码(后台)
    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        boolean b = service.resetPassowrd(username);
        response.sendRedirect("userManage?operator=queryByPage");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

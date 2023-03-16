package com.itz.servlet;

import com.alibaba.fastjson.JSON;
import com.itz.entity.User;
import com.itz.service.CollectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CollectionManageServlet", value = "/collectionManage")
public class CollectionManageServlet extends HttpServlet {
    private CollectionService service = new CollectionService();
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
    //登录状态校验
    public void loginCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null) {
          response.sendRedirect("collection.jsp");
            return;
        }

        // 获取浏览器携带的全部Cookie
        Cookie[] cookies = request.getCookies();
        // 2. 查看是否有用户名username-->admin,password-->123456的cookie
        String value = null; //username对应的cookie值
        if(cookies!=null) {
            // 2.1 遍历所有的cookie对象，来找特定cookie的值
            for(int i=0; i<cookies.length;i++) {
                Cookie cookie = cookies[i];
                String cookieName = cookie.getName();
                if("loginInfo".equals(cookieName)) {
                    value = cookie.getValue();
                }
            }
        }
        if(value!=null){
            response.sendRedirect("collection.jsp");
        }else{
            response.sendRedirect(request.getContextPath()+"/userManage?operator=loginCheck");
        }
    }

    //查询收藏列表
    public void showCollections(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user==null){
            response.getWriter().println("0");
            return;
        }
        String uid=String.valueOf(user.getUid());
        List<Map<String, Object>> list = new ArrayList<>();
        list= service.queryAll(uid);
        String collectionList = JSON.toJSONString(list);
        response.getWriter().println(collectionList);
    }

    //删除收藏的视频
    public void deleteCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vid=request.getParameter("vid");
        service.deleteCollection(vid);
        response.sendRedirect("collection.jsp");
    }

    //添加收藏的视频
    public void addCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String uid=request.getParameter("uid");
        String vid=request.getParameter("vid");
        boolean b=service.addeCollection(uid,vid);
        if(b)
          response.getWriter().write("1");
        else
            response.getWriter().write("0");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

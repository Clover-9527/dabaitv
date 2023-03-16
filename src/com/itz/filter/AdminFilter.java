package com.itz.filter;

import com.itz.entity.User;
import com.itz.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",urlPatterns = "/admin")
public class AdminFilter implements Filter {
    UserService service = new UserService();
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // 1. 获取浏览器携带的cookie
        Cookie[] cookies = req.getCookies();
        String value = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("loginInfo".equals(name)) {
                    value = cookie.getValue(); // username:password
                    break;
                }
            }
            //自动登录ck
            if (value != null) {// 获取用户名和密码：username-password
                String[] parts = value.split(":"); // username,password
                String username = parts[0];
                String password = parts[1];
                // 验证
                if (username.equals("admin")) {
                    User user = new User(username, password);
                    User loginUser = new User();
                    loginUser = service.loginCheck(user); // 业务层返回给web层
                    if (loginUser != null)
                        chain.doFilter(request, response);
                    return;
                }
            }
            resp.sendRedirect("login.jsp");
        }
    }
}

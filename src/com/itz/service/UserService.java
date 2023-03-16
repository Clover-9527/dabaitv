package com.itz.service;

import com.itz.dao.UserDao;
import com.itz.dao.impl.UserDaoImpl;
import com.itz.entity.PageBean;
import com.itz.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao dao = new UserDaoImpl();

    public User findUser(String username) {
        User user = new User();
        try {
            user=dao.findUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean queryPhone(String phone){
        boolean b = false;
        try {
            b= dao.queryPhone(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
    
    public boolean addUser(User user) {
        boolean b = false;
        try {
          b=  dao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean deleteUser(String uid){
        boolean b = false;
        try {
            b=dao.deleteUser(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public User loginCheck(User user){
        User u = new User();
        try {
            u=dao.loginCheck(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean alterUser(User user){
        try {
            return dao.alterUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PageBean<User> queryByPage(int currentPage,int pageSize){
        PageBean<User> pb = new PageBean<>();
        try {
            //存储当前页数
            pb.setCurrentPage(currentPage);
            //存储每页显示的条数
            pb.setPageSize(pageSize);
            //存储要显示的权限数据 List集合,找dao层获取
            //调用dao层方法queryByPage,传递计算好的参数,获取查询结果集
            List<User> list =
                    dao.queryByPage((currentPage - 1) * pageSize, pageSize);
            //集合List,存在到pageBean中
            pb.setList(list);

            //存储共有多少条数据,找dao层获取
            long totalCount = dao.getTotalCount();
            pb.setTotalCount(totalCount);

            //存储一共有多少页
            //计算 (总条数/每页显示的条数) 向上去整  14.0 / 5 = 2.22
            int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
            pb.setTotalPage(totalPage);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return pb;
    }

    public boolean updateUser(Object[] params) {
        boolean b=false;
        try {
            b =dao.updateUser(params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean resetPassowrd(String username) {
        boolean b = false;
        try {
            b = dao.resetPassowrd(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}

package com.itz.dao.impl;

import com.itz.dao.UserDao;
import com.itz.entity.User;
import com.itz.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    QueryRunner qr = C3P0Util.getQueryRunner();
    @Override
    public User findUser(String username) throws SQLException {
        boolean b;
        User user = new User();
        String sql="select * from user where username=?";
        user=qr.query(sql,new BeanHandler<User>(User.class),username);
        return user;
    }

    @Override
    public boolean queryPhone(String phone) throws SQLException {
        String sql="select * from user where phone=?";
        User user = qr.query(sql, new BeanHandler<User>(User.class), phone);
        if(user!=null)
            return false;
        else
            return true;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        boolean b;
        String sql="insert into user values(?,?,?,?,?,?,?)";
        Object[] params = {user.getUid(),user.getUimg(),user.getUsername(),user.getPassword(),user.getNickname(),user.getPhone(),user.isAdmin()};
        int num=  qr.update(sql, params);
        if(num>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean delUser(String username) throws SQLException {
        boolean b;
        String sql="delete from user where username=?";
        int num=qr.update(sql,username);
        if(num>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean resetPassowrd(String username) throws SQLException {
        String sql= "update user set password=123456 where username=?";
        int n = qr.update(sql, username);
        if(n>0)
            return true;
        return false;
    }


    @Override
    public User loginCheck(User user) throws SQLException {
        String username=user.getUsername();
        String password=user.getPassword();
        Object[] params={username,password};
        String sql="select * from user where username=? and password=?";
        User u= new User();
        u=qr.query(sql,new BeanHandler<User>(User.class),params);
        return u;
    }

    @Override
    public boolean alterUser(User user) throws SQLException {
        String sql="update user set uimg=?,password=?,nickname=?,phone=? where uid=?";
        Object[] params={user.getUimg(),user.getPassword(),user.getNickname(),user.getPhone(),user.getUid()};
        int n = qr.update(sql, params);
        if(n>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteUser(String uid) throws SQLException{
        String sql ="delete from user where uid=?";
        int n= qr.update(sql,uid);
        if(n>0)
            return true;
        else
            return false;
    }

    @Override
    public List<User> queryByPage(int currentPage,int pageSize) throws SQLException {
        //拼写分页的查询语句
        String sql = "select * from user limit ?,?";
        //执行查询
        List<User> list = qr.query(sql,new BeanListHandler<User>(User.class),currentPage,pageSize);
        return list;
    }

    // 查询获取权限表的所有数据,聚合函数count
    @Override
    public long getTotalCount() throws SQLException {
        String sql = "select count(uid) from user";
        //执行查询,结果是单值,使用scalarHandler
        return qr.query(sql,new ScalarHandler<Long>());
    }

    @Override
    public boolean updateUser(Object[] params) throws SQLException {
        String sql="update user set nickname=?, username=?, phone=?, uimg=? where uid=?";
        int n = qr.update(sql, params);
        if(n>0)
            return true;
        else
            return false;
    }
}

package com.itz.dao;

import com.itz.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User findUser(String username) throws SQLException;
    boolean queryPhone(String username) throws SQLException;    //验证手机号是否被绑定
    boolean addUser(User user) throws SQLException;
    boolean delUser(String username) throws SQLException;
    boolean resetPassowrd(String username) throws SQLException;
    User loginCheck(User user) throws SQLException;
    boolean alterUser(User user) throws SQLException;
    boolean deleteUser(String uid) throws SQLException;

    List<User> queryByPage(int currentPage,int pageSize) throws SQLException;
    long getTotalCount() throws SQLException;

    boolean updateUser(Object[] params) throws SQLException;
}

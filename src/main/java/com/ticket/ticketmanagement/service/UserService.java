package com.ticket.ticketmanagement.service;

import com.ticket.ticketmanagement.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 根据id查
     */
    List<User> selectById(int id);
    /**
     * 添加用户
     */
    int addUser(User user);
    /**
     * 查询所有
     */
    List<User> selectAll();
    /**
     *根据id删除
     */
    int deleteById(int id);
    /**
     *修改
     */
    int update(User user);
    /**
     *查询By
     */
    List<User>selectBy(User user);



}

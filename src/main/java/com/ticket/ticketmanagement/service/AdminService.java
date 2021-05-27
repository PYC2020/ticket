package com.ticket.ticketmanagement.service;

import com.ticket.ticketmanagement.entity.Admin;
import com.ticket.ticketmanagement.entity.User;

import java.util.List;

public interface AdminService {
    //根据id查
    List<Admin> selectById(int id);
    //添加用户
    int addAdmin(Admin admin);
    //查询所有
    List<Admin> selectAll();
    //根据id删除
    int deleteById(int id);
}

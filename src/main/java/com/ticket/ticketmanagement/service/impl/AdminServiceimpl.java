package com.ticket.ticketmanagement.service.impl;

import com.ticket.ticketmanagement.entity.Admin;
import com.ticket.ticketmanagement.entity.User;
import com.ticket.ticketmanagement.mapper.impl.AdminMapper;
import com.ticket.ticketmanagement.mapper.impl.UserMapper;
import com.ticket.ticketmanagement.service.AdminService;
import com.ticket.ticketmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AdminServiceimpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Admin> selectById(int id) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        List list = adminMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addAdmin(Admin admin) {
        return 0;
    }

    @Override
    public List<Admin> selectAll() {
        return null;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }
}

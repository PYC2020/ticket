package com.ticket.ticketmanagement.service.impl;

import com.ticket.ticketmanagement.entity.User;
import com.ticket.ticketmanagement.mapper.impl.UserMapper;
import com.ticket.ticketmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @author ACER
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<User> selectById(int id) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        List list = userMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addUser(User user) {
        if (user.getRealName() != null && user.getPassword() != null && user.getIdentityCard() != null) {
            //System.out.println(user);
            return userMapper.insert(user);
        }
        return 0;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int deleteById(int id) {
        int i = userMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int update(User user) {
        //updateByPrimaryKeySelective
        //
        //updateByPrimaryKey
        //前者只是更新新的model中不为空的字段。
        //
        //后者则会将为空的字段在数据库中置为NULL。
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    @Override
    public List<User> selectBy(User user) {
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user.getIdentityCard()!=null){
            criteria.andEqualTo("identityCard",user.getIdentityCard());
        }
        if(user.getRealName()!=null){
            criteria= criteria.andEqualTo("identityCard", user.getIdentityCard());
        }if(user.getIdentityCard()!=null){
            criteria=criteria.andEqualTo("realName",user.getRealName());
        }if(user.getName()!=null){
            criteria=criteria.andEqualTo("name",user.getName());
        }if(user.getStatus()!=null){
            criteria=criteria.andEqualTo("status",user.getStatus());
        }if(user.getPassword()!=null){
            criteria=criteria.andEqualTo("password",user.getPassword());
        }
        return userMapper.selectByExample(example);
    }


}

package com.ticket.ticketmanagement.service.impl;

import com.ticket.ticketmanagement.entity.Cart;
import com.ticket.ticketmanagement.mapper.impl.CartMapper;
import com.ticket.ticketmanagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: ticket-management
 * @description:
 * @author: Joe
 * @create: 2021-05-27 13:49
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public List<Cart> selectById(int id) {
        Example example=new Example(Cart.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("id",id);
        List<Cart> list=cartMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addCart(Cart cart) {
        if (cart.getPrice()!=null && cart.getStatus()!=null && cart.getTime()!=null && cart.getUid()!=null) {
            return cartMapper.insert(cart);
        }
        return 0;
    }

    @Override
    public List<Cart> selectAll() {
        return cartMapper.selectAll();
    }

    @Override
    public int deleteById(int id) {
        return cartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Cart cart) {
        return cartMapper.updateByPrimaryKeySelective(cart);
    }
}

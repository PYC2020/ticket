package com.ticket.ticketmanagement.service;

import com.ticket.ticketmanagement.entity.Car;
import com.ticket.ticketmanagement.entity.Cart;

import java.util.List;

/**
 * @program: ticket-management
 * @description:
 * @author: Joe
 * @create: 2021-05-27 13:47
 */
public interface CartService {

    List<Cart> selectById(int id);

    int addCart(Cart cart);

    List<Cart> selectAll();


    int deleteById(int id);

    int update(Cart cart);

}

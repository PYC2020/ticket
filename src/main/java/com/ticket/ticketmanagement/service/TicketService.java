package com.ticket.ticketmanagement.service;

import com.ticket.ticketmanagement.entity.Cart;
import com.ticket.ticketmanagement.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> selectById(int id);

    int addTicket(Ticket ticket);

    List<Ticket> selectAll();


    int deleteById(int id);

    int update(Ticket ticket);

}

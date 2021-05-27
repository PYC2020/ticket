package com.ticket.ticketmanagement.service.impl;

import com.ticket.ticketmanagement.entity.Ticket;
import com.ticket.ticketmanagement.mapper.impl.TicketMapper;
import com.ticket.ticketmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: ticket-management
 * @description:
 * @author: Joe
 * @create: 2021-05-27 14:02
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;

    @Override
    public List<Ticket> selectById(int id) {
        Example example=new Example(Ticket.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("id",id);
        List<Ticket> list=ticketMapper.selectByExample(example);
        return list;
    }

    @Override
    public int addTicket(Ticket ticket) {
        if(ticket.getCarNumber()!=null && ticket.getEndAddress()!=null && ticket.getEndTime()!=null
                &&ticket.getPrice()!=null&&ticket.getStartAddress()!=null && ticket.getStartTime()!=null &&
            ticket.getType()!=null && ticket.getUid()!=null){
            return ticketMapper.insert(ticket);
        }
        return 0;
    }

    @Override
    public List<Ticket> selectAll() {
        return ticketMapper.selectAll();
    }

    @Override
    public int deleteById(int id) {
        return ticketMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Ticket ticket) {
        return ticketMapper.updateByPrimaryKeySelective(ticket);
    }
}

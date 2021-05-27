package com.ticket.ticketmanagement.controller;

import com.ticket.ticketmanagement.entity.Cart;
import com.ticket.ticketmanagement.entity.Response;
import com.ticket.ticketmanagement.entity.Ticket;
import com.ticket.ticketmanagement.service.CartService;
import com.ticket.ticketmanagement.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: ticket-management
 * @description:
 * @author: Joe
 * @create: 2021-05-27 14:15
 */
@Controller
@Api("车票管理")
@RequestMapping("/ticket")
public class TicketController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);
    @Autowired
    TicketService ticketService;
    @ResponseBody
    @ApiOperation(value = "查询Ticket", notes = "根据主键进行")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "没有该数据")
    })
    @GetMapping(value = "/{id}")
    public Response<List<Ticket>> selectById(@PathVariable Integer id){
        Response<List<Ticket>> res = new Response<>();
        List<Ticket> tickets = ticketService.selectById(id);
        if(!tickets.isEmpty()){
            res.setData(tickets);
            res.setCode(200);
            res.setMsg("状态正常");
        }else {
            res.setData(null);
            res.setCode(400);
            res.setMsg("没有该数据");
        }
        return res;

    }
    @ResponseBody
    @ApiOperation(value ="查询所有",notes = "Ticket表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "服务异常")
    })
    @GetMapping(value = "/getAll")
    public Response<List<Ticket>>getAll(){
        Response<List<Ticket>> res=new Response<>();
        List<Ticket> list=ticketService.selectAll();
        if(list!=null){
            res.setData(list);
            res.setCode(200);
            res.setMsg("状态正常");
        }else {
            res.setData(null);
            res.setCode(400);
            res.setMsg("服务异常");
        }
        return res;
    }
    @ResponseBody
    @ApiOperation(value ="添加车票",notes = "Ticket表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 400, message = "添加失败")
    })
    @PostMapping(value = "/addTicket")
    public Response<Integer>addTicket(@RequestBody Ticket ticket
    ){
        Response<Integer>res=new Response<>();
        int result=ticketService.addTicket(ticket);
        if(result==1){
            res.setData(result);
            res.setCode(200);
            res.setMsg("添加成功");
        }else {
            res.setData(null);
            res.setCode(400);
            res.setMsg("添加失败");
        }
        return res;
    }
    @ResponseBody
    @ApiOperation(value = "删除车票",notes = "根据主键")
    @ApiResponses({
            @ApiResponse(code=200,message = "删除成功"),
            @ApiResponse(code=200,message = "删除失败")
    })

    @DeleteMapping("/deleteById/{id}")
    public Response<Integer>deleteById(@PathVariable("id") int id){
        Response<Integer>res=new Response<>();
        int list=ticketService.deleteById(id);
        if(list==1){
            res.setData(list);
            res.setCode(200);
            res.setMsg("删除成功");
        }else {
            res.setData(null);
            res.setCode(400);
            res.setMsg("删除失败，没有该值");
        }
        return res;
    }
    @ResponseBody
    @ApiOperation(value ="修改车票",notes = "可支持单个修改以及多个修改Ticket表根据主键")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "修改失败")
    })
    @PostMapping(value = "/updateTicket")
    public Response<Integer>updateTicket(@RequestBody Ticket ticket
    ){
        Response<Integer>res=new Response<>();
        int list=ticketService.update(ticket);
        if(list==1){
            res.setData(list);
            res.setCode(200);
            res.setMsg("修改成功");
        }else {
            res.setData(null);
            res.setCode(400);
            res.setMsg("修改失败，没有该值");
        }
        return res;
    }
}

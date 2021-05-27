package com.ticket.ticketmanagement.controller;

import com.ticket.ticketmanagement.entity.Car;
import com.ticket.ticketmanagement.entity.Cart;
import com.ticket.ticketmanagement.entity.Response;
import com.ticket.ticketmanagement.service.CartService;
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
 * @create: 2021-05-27 13:57
 */
@Controller
@Api("订单管理")
@RequestMapping("/cart")
public class CartController {

    private static Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    CartService cartService;
    @ResponseBody
    @ApiOperation(value = "查询Cart", notes = "根据主键进行")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "没有该数据")
    })
    @GetMapping(value = "/{id}")
    public Response<List<Cart>> selectById(@PathVariable Integer id){
        Response<List<Cart>> res = new Response<>();
        List<Cart> carts = cartService.selectById(id);
        if(!carts.isEmpty()){
            res.setData(carts);
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
    @ApiOperation(value ="查询所有",notes = "Cart表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "服务异常")
    })
    @GetMapping(value = "/getAll")
    public Response<List<Cart>>getAll(){
        Response<List<Cart>> res=new Response<>();
        List<Cart> list=cartService.selectAll();
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
    @ApiOperation(value ="添加订单",notes = "Cart表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 400, message = "添加失败")
    })
    @PostMapping(value = "/addCart")
    public Response<Integer>addCart(@RequestBody Cart cart
    ){
        Response<Integer>res=new Response<>();
        int result=cartService.addCart(cart);
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
    @ApiOperation(value = "删除订单",notes = "根据主键")
    @ApiResponses({
            @ApiResponse(code=200,message = "删除成功"),
            @ApiResponse(code=200,message = "删除失败")
    })

    @DeleteMapping("/deleteById/{id}")
    public Response<Integer>deleteById(@PathVariable("id") int id){
        Response<Integer>res=new Response<>();
        int list=cartService.deleteById(id);
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
    @ApiOperation(value ="修改订单",notes = "可支持单个修改以及多个修改Cart表根据主键")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "修改失败")
    })
    @PostMapping(value = "/updateCart")
    public Response<Integer>updateCart(@RequestBody Cart cart
    ){
        Response<Integer>res=new Response<>();
        int list=cartService.update(cart);
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

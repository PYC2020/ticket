package com.ticket.ticketmanagement.controller;

import com.ticket.ticketmanagement.entity.Admin;
import com.ticket.ticketmanagement.entity.Response;
import com.ticket.ticketmanagement.entity.User;
import com.ticket.ticketmanagement.service.AdminService;
import com.ticket.ticketmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@Api("用户管理")
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    AdminService adminService;

    @ResponseBody
    @ApiOperation(value = "查询Admin", notes = "根据主键进行")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "服务异常")
    })
    @GetMapping(value = "/{id}")
    public Response<List<Admin>> selectbyid(@PathVariable Integer id){
        Response<List<Admin>> res = new Response<>();
        List<Admin> admins = adminService.selectById(id);
        res.setData(admins);
        return res;

    }
}

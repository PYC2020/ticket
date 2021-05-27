package com.ticket.ticketmanagement.controller;

import com.ticket.ticketmanagement.entity.Response;
import com.ticket.ticketmanagement.entity.User;
import com.ticket.ticketmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Api("用户管理")
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @ResponseBody
    @ApiOperation(value = "查询User", notes = "根据主键进行")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "没有该数据")
    })
    @GetMapping(value = "/{id}")
    public Response<List<User>> selectbyid(@PathVariable Integer id){
        Response<List<User>> res = new Response<>();
        List<User> users = userService.selectById(id);
        if(!users.isEmpty()){
            res.setData(users);
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
    @ApiOperation(value ="查询所有",notes = "User表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "服务异常")
    })
    @GetMapping(value = "/getAll")
    public Response<List<User>>getall(){
        Response<List<User>>res=new Response<>();
        List<User>list=userService.selectAll();
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
    @ApiOperation(value ="添加用户",notes = "User表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 400, message = "添加失败")
    })
    @PostMapping(value = "/addUser")
    public Response<Integer>addUser(@RequestBody User user
                                    ){
        Response<Integer>res=new Response<>();
        int list=userService.addUser(user);
        if(list==1){
            res.setData(list);
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
    @ApiOperation(value = "删除用户",notes = "根据主键")
    @ApiResponses({
            @ApiResponse(code=200,message = "删除成功"),
            @ApiResponse(code=200,message = "删除失败")
    })

    @DeleteMapping("/deleteById/{id}")
    public Response<Integer>deleteById(@PathVariable("id") int id){
        Response<Integer>res=new Response<>();
        int list=userService.deleteById(id);
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
    @ApiOperation(value ="修改用户",notes = "可支持单个修改以及多个修改User表根据主键")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "修改失败")
    })
    @PostMapping(value = "/updateUser")
    public Response<Integer>updateUser(@RequestBody User user
    ){
        Response<Integer>res=new Response<>();
        int list=userService.update(user);
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

    @ResponseBody
    @ApiOperation(value ="登录",notes = "User/登陆成功状态设置为1")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 401, message = "用户名或者密码不能为空"),
            @ApiResponse(code = 402, message = "用户名不存在"),
            @ApiResponse(code = 403, message = "用户密码错误")
    })
    @PostMapping(value = "/login")
    public Response<Integer>login(HttpSession session, @RequestParam("name") String name, @RequestParam("password") String password){
        Response<Integer>res=new Response<>();
        if(name.trim()==null||password.trim()==null||name.trim().length()==0||password.trim().length()==0){
            res.setData(null);
            res.setMsg("用户名或者密码不能为空");
            res.setCode(401);
            return res;
        }
        User user=new User();
        user.setName(name);
        List<User> list = userService.selectBy(user);
        if(list.isEmpty()){
            res.setData(null);
            res.setMsg("用户名不存在");
            res.setCode(402);
            return res;
        }
        user.setPassword(password);
        List<User> list1 = userService.selectBy(user);
        if(list1.isEmpty()){
            res.setData(null);
            res.setMsg("用户密码错误");
            res.setCode(403);
            return res;
        }
        if(list1.size()==1){
            String []arr=list1.get(0).toString().split(",");
            Long id = Long.valueOf(arr[0].substring(8, arr[0].length()));
            User user1=new User();
            user1.setId(id);
            user1.setStatus(1);
            int update = userService.update(user1);
            session.setAttribute("User",list1);
            session.setAttribute("id",id);
            res.setData(update);
            res.setMsg("登陆成功");
            res.setCode(200);
            return res;
        }
        return res;
    }
    @ResponseBody
    @ApiOperation(value ="注销",notes = "User/登陆成功状态设置为0")
    @ApiResponses({
            @ApiResponse(code = 200, message = "注销成功"),
            @ApiResponse(code = 500, message = "注销失败")
    })
    @PostMapping("/logout")
    public Response logout(HttpSession session){
        //@ModelAttribute("User")相当于将session中名为"User"的对象注入user对象中
        //sessionStatus中的setComplete方法可以将session中的内容全部清空
        Object id =  session.getAttribute("id");
        Response<Integer> res = new Response();
        if(id!=null) {
            User user = new User();
            user.setId(Long.valueOf(id.toString()));
            user.setStatus(0);
            int update = userService.update(user);
            session.removeAttribute("User");
            res.setCode(200);
            res.setMsg("注销成功");
            res.setData(update);
        }else {
            res.setCode(500);
            res.setMsg("注销失败");
            res.setData(null);
        }
        return res;
    }
    @ResponseBody
    @ApiOperation(value ="注册",notes = "User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "注册成功"),
            @ApiResponse(code = 401, message = "该证件已被注册"),
            @ApiResponse(code = 402, message = "该手机号已被注册"),
            @ApiResponse(code = 404, message = "注释项不能为空")
    })
    @PostMapping("/register")
    public Response<Integer>register(@RequestBody User user){
        Response<Integer>res=new Response<>();
        if(user.getRealName()==null
                ||user.getPhone()==null
                ||user.getIdentityCard()==null
                ||user.getPassword()==null
                ||user.getName().trim().length()==0
                ||user.getRealName().trim().length()==0
                ||user.getIdentityCard().trim().length()==0
                ||user.getPhone().trim().length()==0){
            res.setData(null);
            res.setMsg("注释项不能为空");
            res.setCode(404);
            return res;
        }
        User user1=new User();
        user1.setName(user.getName());
        List<User> list1 = userService.selectBy(user1);
        if(!list1.isEmpty()){
            res.setData(null);
            res.setMsg("该昵称已存在");
            res.setCode(400);
            return res;
        }
        User user2=new User();
        user2.setIdentityCard(user.getIdentityCard());
        List<User> list2 = userService.selectBy(user2);
        if(!list2.isEmpty()){
            res.setData(null);
            res.setMsg("该证件已被注册");
            res.setCode(401);
            return res;
        }
        User user3=new User();
        user3.setPhone(user.getPhone());
        List<User> list3 = userService.selectBy(user3);
        if(!list3.isEmpty()){
            res.setData(null);
            res.setMsg("该手机号已被注册");
            res.setCode(402);
            return res;
        }
        int list=userService.addUser(user);
        res.setData(list);
        res.setMsg("注册成功");
        res.setCode(200);
        return res;
    }
}

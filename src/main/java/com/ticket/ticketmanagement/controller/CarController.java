package com.ticket.ticketmanagement.controller;

import com.ticket.ticketmanagement.entity.Car;
import com.ticket.ticketmanagement.entity.Response;
import com.ticket.ticketmanagement.entity.User;
import com.ticket.ticketmanagement.service.CarService;
import com.ticket.ticketmanagement.service.UserService;
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
 * @create: 2021-05-27 13:35
 */
@Controller
@Api("车次管理")
@RequestMapping("/car")
public class CarController {

    private static Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    CarService carService;
    @ResponseBody
    @ApiOperation(value = "查询Car", notes = "根据主键进行")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "没有该数据")
    })
    @GetMapping(value = "/{id}")
    public Response<List<Car>> selectById(@PathVariable Integer id){
        Response<List<Car>> res = new Response<>();
        List<Car> cars = carService.selectById(id);
        if(!cars.isEmpty()){
            res.setData(cars);
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
    @ApiOperation(value ="查询所有",notes = "Car表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "状态正常"),
            @ApiResponse(code = 400, message = "服务异常")
    })
    @GetMapping(value = "/getAll")
    public Response<List<Car>>getAll(){
        Response<List<Car>> res=new Response<>();
        List<Car> list=carService.selectAll();
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
    @ApiOperation(value ="添加车次",notes = "Car表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 400, message = "添加失败")
    })
    @PostMapping(value = "/addCar")
    public Response<Integer>addCar(@RequestBody Car car
    ){
        Response<Integer>res=new Response<>();
        int result=carService.addCar(car);
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
    @ApiOperation(value = "删除车次",notes = "根据主键")
    @ApiResponses({
            @ApiResponse(code=200,message = "删除成功"),
            @ApiResponse(code=200,message = "删除失败")
    })

    @DeleteMapping("/deleteById/{id}")
    public Response<Integer>deleteById(@PathVariable("id") int id){
        Response<Integer>res=new Response<>();
        int list=carService.deleteById(id);
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
    @ApiOperation(value ="修改车次",notes = "可支持单个修改以及多个修改Car表根据主键")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "修改失败")
    })
    @PostMapping(value = "/updateCar")
    public Response<Integer>updateCar(@RequestBody Car car
    ){
        Response<Integer>res=new Response<>();
        int list=carService.update(car);
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

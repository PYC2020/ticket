package com.ticket.ticketmanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回对象")
public class Response<T> {
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}

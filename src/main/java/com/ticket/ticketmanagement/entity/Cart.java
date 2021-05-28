package com.ticket.ticketmanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel("订单对象模型")
public class Cart {
  @Id  // JPA注解,指定此属性为表中的主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
  @ApiModelProperty("id")
  private Long id;
  @ApiModelProperty("关联用户id")
  private Long uid;
  @ApiModelProperty("订单状态")
  private String status;
  @ApiModelProperty("订单价格")
  private Double price;
  @ApiModelProperty("下单时间")
  private java.sql.Timestamp time;

}

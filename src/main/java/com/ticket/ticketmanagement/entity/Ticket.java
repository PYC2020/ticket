package com.ticket.ticketmanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel("车票对象模型")
public class Ticket {
  @Id  // JPA注解,指定此属性为表中的主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
  @ApiModelProperty("id")
  private Long id;
  @ApiModelProperty("关联用户id")
  private Long uid;
  @ApiModelProperty("开始地址")
  private String startAddress;
  @ApiModelProperty("下车地址")
  private String endAddress;
  @ApiModelProperty("开始时间")
  private java.sql.Timestamp startTime;
  @ApiModelProperty("结束时间")
  private java.sql.Timestamp endTime;
  @ApiModelProperty("价格")
  private double price;
  @ApiModelProperty("车票类型")
  private String type;
  @ApiModelProperty("车牌号")
  private String carNumber;

}

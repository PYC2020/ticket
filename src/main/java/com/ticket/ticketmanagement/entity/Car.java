package com.ticket.ticketmanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel("车次对象模型")
public class Car {
  @Id  // JPA注解,指定此属性为表中的主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
  @ApiModelProperty("id")
  private Long id;
  @ApiModelProperty("开始地点")
  private String startAddress;
  @ApiModelProperty("结束地点")
  private String endAddress;
  @ApiModelProperty("开始时间")
  private java.sql.Timestamp startTime;
  @ApiModelProperty("结束时间")
  private java.sql.Timestamp endTime;
  @ApiModelProperty("车牌号")
  private String carNumber;
  @ApiModelProperty("车辆状态（是否满员，是否运行）")
  private String status;

}

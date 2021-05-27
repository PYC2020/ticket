package com.ticket.ticketmanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel("Admin对象模型")
public class Admin {
  @Id  // JPA注解,指定此属性为表中的主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
  @ApiModelProperty("id")
  private Long id;
  @ApiModelProperty("名字")
  private String name;
  @ApiModelProperty("密码")
  private String password;
  @ApiModelProperty("权限")
  private String role;

}

package com.ticket.ticketmanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
//@Table(name = "user")
@ApiModel("用户对象模型")
public class User {
  @Id  // JPA注解,指定此属性为表中的主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
  @ApiModelProperty("id")
  private Long id;
  @ApiModelProperty("昵称")
  private String name;
  @ApiModelProperty("真实姓名")
  private String realName;
  @ApiModelProperty("密码")
  private String password;
  @ApiModelProperty("身份证")
  private String identityCard;
  @ApiModelProperty("电话")
  private String phone;
  @ApiModelProperty("邮箱")
  private String mail;
  @ApiModelProperty("性别，0为男，1为女，2为变性")
  private Integer sex;
  @ApiModelProperty("登录状态,0为注销，1为登录")
  private Integer status;



}

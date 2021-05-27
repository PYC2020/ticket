package com.ticket.ticketmanagement;

import com.ticket.ticketmanagement.entity.User;
import com.ticket.ticketmanagement.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TicketManagementApplicationTests {
//    @Autowired
//    UserMapper mapper1;
    @Autowired
    private UserService userService;
//    private static final Logger logger = Logger.getLogger(TicketManagementApplicationTests.class.getName());
//    @Autowired
//    TagMapper2 mapper2;
//    @Autowired
//    RedisTemplate<String, String> redisTemplate;
//
    @Test
    public void test1() {
        // 注解方式
        User user = new User();
        for (int i = 0; i < 100; i++) {
            user.setPhone((Math.random() * 29999999999l) + "");
            user.setMail((Math.random() * 999999999) + "@qq.com");
            user.setIdentityCard((Math.random() * 999999999999999999l)+ "");
            user.setName(UUID.randomUUID().toString().substring(0,8));
            user.setRealName(UUID.randomUUID().toString().substring(0,3));
            user.setPassword(UUID.randomUUID().toString().substring(0,8));
            user.setSex(new Random().nextInt(2));
            userService.addUser(user);
        }

    }
//
//    @Test
//    void test2() {
//        // xml映射文件方式 查询
//        System.out.println(mapper2.getAllTag());
//    }

}

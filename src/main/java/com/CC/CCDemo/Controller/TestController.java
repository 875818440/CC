package com.CC.CCDemo.Controller;

import com.CC.CCDemo.Dao.UserDao;
import com.CC.CCDemo.Demo.User;
import com.CC.CCDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@RestController
public class TestController {
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "/test";
    }

    @RequestMapping("/toHello")
    public String toHello(ModelMap modelMap){
       /// List<User> list=userService.findStudentByName("Mshu2");
        Long  id=new Long("3");
       /// Optional<User> user=userService.findById(id);
       // List<User> users=userService.findByRegTime("1");
        User user =new User();
        user.setEmail("jin@qq.com");
        user.setNickName("jin4");
        user.setPassWord("123");
        user.setRegTime("1");
        user.setUserName("Mshu4");
       //  userService.saveUser(user);
       // userService.updateById();
       // userService.findAndUpdateById(Long.parseLong("7"));
        userService.saveUserRole();
       return  userService.buildPageRequest();
    }
}

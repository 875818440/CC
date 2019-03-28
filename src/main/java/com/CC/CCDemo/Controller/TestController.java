package com.CC.CCDemo.Controller;

import com.CC.CCDemo.Dao.UserRepository;
import com.CC.CCDemo.Demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/test")
    public String test(){
        return "/test";
    }

    @RequestMapping("/toHello")
    public String toHello(ModelMap modelMap){
        userRepository.save(new User("Mshu","123456","jin@qq.com","jin"));
        List<User> users = userRepository.findAll();
        modelMap.put("users",users);
        return "helloBoot";
    }
}

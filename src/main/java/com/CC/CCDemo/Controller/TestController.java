package com.CC.CCDemo.Controller;

import com.CC.CCDemo.Dao.UserDao;
import com.CC.CCDemo.Demo.Area;
import com.CC.CCDemo.Demo.Role;
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
        user.setId(Long.parseLong("7"));
       //  userService.saveUser(user);
       // userService.updateById();
       // userService.findAndUpdateById(Long.parseLong("7"));
       // userService.saveUserRole();
         Area areaQue=new Area();
        areaQue.setAreaName("上海浦东新区");
        areaQue.setId(1);
        areaQue.setStatus(0);
         List<User> users=userService.findAll(user,24,areaQue);
         StringJoiner  areaJoiner=new StringJoiner("-");
        StringJoiner  roleJoiner=new StringJoiner(",");
        for (User user1:users) {
            for (Area area:user1.getAreas()
                 ) {
                areaJoiner.add(area.getAreaName());
            }
            for (Role role:user1.getRoles()
                 ) {
                roleJoiner.add(role.getRoleName());
            }
        }
       return  areaJoiner+"/////"+roleJoiner;
    }
}

package com.CC.CCDemo.Service;

import com.CC.CCDemo.Dao.RoleDao;
import com.CC.CCDemo.Dao.UserDao;
import com.CC.CCDemo.Demo.Role;
import com.CC.CCDemo.Demo.SortDto;
import com.CC.CCDemo.Demo.User;
import com.CC.CCDemo.SortTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    public List<User> findStudentByName(String name){
        return userDao.findByUserName(name);
    }
    public Optional<User> findById(Long Id){
        return userDao.findById(Id);
    }
    public void findAndUpdateById(Long Id){
        Optional<User> userOptional=userDao.findById(Id);
        User user;
        user = userOptional.orElseGet(User::new);
        user.setNickName("test111");
        userDao.save(user);
    }
    public List<User> findByRegTime(String regTime){
        return userDao.findByRegTime(regTime);
    }
    public void saveUser(User user){
        userDao.save(user);
    }
    public void updateById(){
        User user=new User();
        Long id=new Long("7");
        user.setId(id );
        user.setUserName("test2");
     userDao.updateById("test3",id);
    }
    public void saveUserRole(){
        User user=new User();
        Role role=new Role();
        user=userDao.findById(Long.parseLong("7")).orElseGet(User::new);
        role.setRoleName("系统维护员");
        role=roleDao.findByRoleName("系统维护员").orElse(new Role("系统维护员"));
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        Role role1=new Role();
        role1.setRoleName("测试");
        role1=roleDao.findByRoleName("测试").orElse(new Role("测试"));
        roleSet.add(role1);
        roleDao.save(role);
        roleDao.save(role1);
        user.setRoles(roleSet);
        userDao.save(user);
    }
    public String buildPageRequest() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
      //  List<User> userList=userDao.findAll(sort);
        List<User> userList=userDao.findAll(SortTools.basicSort(new SortDto("asc","id"),new SortDto("nickName")));
        StringJoiner stringJoiner=new StringJoiner("-");
        for (User u:userList             ) {
            stringJoiner.add(u.getNickName());
        }
        return stringJoiner.toString();
    }
}

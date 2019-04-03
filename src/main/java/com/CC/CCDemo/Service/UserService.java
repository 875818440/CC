package com.CC.CCDemo.Service;

import com.CC.CCDemo.Dao.UserDao;
import com.CC.CCDemo.Demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    public List<User> findStudentByName(String name){
        return userDao.findByUserName(name);
    }
    public User findById(Long Id){
        return userDao.findById(Id);
    }
}

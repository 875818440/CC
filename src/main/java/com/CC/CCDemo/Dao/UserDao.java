package com.CC.CCDemo.Dao;

import com.CC.CCDemo.Demo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface UserDao extends Repository<User,Long> {

    List<User> findByUserName(String name);

    User findById(Long id);
}

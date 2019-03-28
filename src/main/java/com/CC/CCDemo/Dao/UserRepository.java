package com.CC.CCDemo.Dao;

import com.CC.CCDemo.Demo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User,Long> {
}

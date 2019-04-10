package com.CC.CCDemo.Dao;

import com.CC.CCDemo.Demo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserDao extends JpaRepository<User,Long> {

    List<User> findByUserName(String name);

    Optional<User> findById(Long id);

    List<User> findByRegTime(String regTime);
    @Modifying
    @Transactional
    @Query("update  User  set nick_name= :nickName where id= :id")
    void  updateById(@Param("nickName")String nickName, @Param("id")Long id);

    User findByNickName(String nickName);
}

package com.CC.CCDemo.Dao;

import com.CC.CCDemo.Demo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface RoleDao extends JpaRepository<Role,Integer> {
Optional<Role> findByRoleName(String roleName);

}

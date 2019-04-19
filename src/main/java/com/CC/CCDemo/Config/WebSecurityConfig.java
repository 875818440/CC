package com.CC.CCDemo.Config;


import com.CC.CCDemo.Dao.UserRepository;
import com.CC.CCDemo.Demo.User;
import com.CC.CCDemo.Service.MyUserDetailsService;
import com.CC.CCDemo.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by EalenXie on 2018/1/11.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private EvolutionaryAuthenticationSuccessHandler evolutionaryAuthenticationSuccessHandler;

    /**
     * 1）HttpSecurity支持cors。
     * 2）默认会启用CRSF，此处因为没有使用thymeleaf模板（会自动注入_csrf参数），
     * 要先禁用csrf，否则登录时需要_csrf参数，而导致登录失败。
     * 3）antMatchers：匹配 "/" 路径，不需要权限即可访问，匹配 "/user" 及其以下所有路径，
     * 都需要 "USER" 权限
     * 4）配置登录地址和退出地址
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/hello")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()  //表单登录
                //.loginPage("/evolutionary-loginIn.html")
                .loginPage("/logintype") //如果需要身份认证则跳转到这里
                .loginProcessingUrl("/login")
                .successHandler(evolutionaryAuthenticationSuccessHandler)
                //   .failureHandler(evolutionaryAuthenticationFailureHandler)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/logintype", securityProperties.getBrower().getLoginPage())//不校验我们配置的登录页面
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

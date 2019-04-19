package com.CC.CCDemo.Controller;

import com.CC.CCDemo.Config.SecurityProperties;
import com.CC.CCDemo.Config.WebSecurityConfig;
import com.CC.CCDemo.Demo.User;
import com.CC.CCDemo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserService userService;

    @RequestMapping("/logintype")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null||(savedRequest!=null&&("addUser".contains(savedRequest.getRedirectUrl())||"addUser_html".contains(savedRequest.getRedirectUrl())))) {
            String targetUrl =  savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是：" + targetUrl);
            //if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrower().getLoginPage());
          //  }
        }
        return "请登录!";
    }


    @GetMapping("/home_html")
    public String homeHtml(){
        return "home";
    }

    @GetMapping("/login_html")
    public String loginHtml(){
        return "login";
    }

    @PostMapping("/login")
    public void login(){
    }
    @RequestMapping("/addUser_html")
    public String addUserHtml(){
        return "addUser";
    }
    @RequestMapping("/addUser")
    public void addUser(User userForm){
        userService.addUser(userForm);
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

}

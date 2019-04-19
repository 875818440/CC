package com.CC.CCDemo.Config;

public class BrowerProperties {
    private String loginPage = "/login_html";//默认跳转的接口

//    private LoginInType loginInType = LoginInType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

//    public LoginInType getLoginInType() {
//        return loginInType;
//    }
//
//    public void setLoginInType(LoginInType loginInType) {
//        this.loginInType = loginInType;
//    }
}
package com.CC.CCDemo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityProperties {

    private BrowerProperties brower = new BrowerProperties();

    public BrowerProperties getBrower() {
        return brower;
    }

    public void setBrower(BrowerProperties brower) {
        this.brower = brower;
    }
}
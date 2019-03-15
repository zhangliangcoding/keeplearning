package com.learning.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangll
 * @Date: 2019/3/15 14:29
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {

    private Integer port;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}

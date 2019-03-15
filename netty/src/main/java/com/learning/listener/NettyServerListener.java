package com.learning.listener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangll
 * @Date: 2019/3/15 14:24
 * @Description:
 */
@Component
public class NettyServerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerListener.class);

    /**
     * bootstrap
     */
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * boss
     */
    EventLoopGroup boss = new NioEventLoopGroup();

    /**
     * worker
     */
    EventLoopGroup worker = new NioEventLoopGroup();




}

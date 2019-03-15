package com.learning.component;

import com.learning.common.NettyConstant;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: zhangll
 * @Date: 2019/3/15 14:31
 * @Description:
 */
@Component
public class RequestDispatcher implements ApplicationContextAware {

    private ExecutorService executorService = Executors.newFixedThreadPool(NettyConstant.MAX_THREAD);

    private ApplicationContext app;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.app = applicationContext;
    }
}


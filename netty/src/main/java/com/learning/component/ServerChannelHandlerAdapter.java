package com.learning.component;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangll
 * @Date: 2019/3/15 14:28
 * @Description:
 */
@Component
@ChannelHandler.Sharable
public class ServerChannelHandlerAdapter extends ChannelHandlerAdapter {



}

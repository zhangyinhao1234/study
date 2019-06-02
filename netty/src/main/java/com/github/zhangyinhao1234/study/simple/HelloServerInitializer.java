package com.github.zhangyinhao1234.study.simple;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 15:33
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //添加一个处理起
        logger.debug("初始化Handler。。。。。。");
        ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new HelloServerHandler());
    }
}

package com.github.zhangyinhao1234.study.simple;

import com.alibaba.fastjson.JSON;
import com.github.zhangyinhao1234.study.protocol.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 15:30
 */
public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
    Logger logger = LoggerFactory.getLogger(HelloServerHandler.class);
    /**
     * 接收到客户端的信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        logger.debug("收到客户端数据："+msg);
        ctx.writeAndFlush("success gogo");
    }
}

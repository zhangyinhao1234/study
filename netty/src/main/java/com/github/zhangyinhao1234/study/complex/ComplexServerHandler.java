package com.github.zhangyinhao1234.study.complex;

import com.alibaba.fastjson.JSON;
import com.github.zhangyinhao1234.study.protocol.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 21:19
 */
public class ComplexServerHandler extends SimpleChannelInboundHandler<RemotingCommand> {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RemotingCommand msg) throws Exception {
        logger.debug("Server msg ......");
        logger.debug("server uid:"+msg.getUid());
    }
}

package com.github.zhangyinhao1234.study.complex;

import com.github.zhangyinhao1234.study.protocol.RemotingCommand;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 21:37
 */
public class ComplexClientUp {

    String ip = "127.0.0.1";
    int port = 9998;
    public static void main(String[] args) throws InterruptedException, IOException {
        new ComplexClientUp().start();
    }

    private void start() throws InterruptedException, IOException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ObjectEncoder(),new ComplexClientHandler());
                    }
                });
        Channel ch = bootstrap.connect(ip, port).sync().channel();

        // 控制台输入
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (; ; ) {
            String line = in.readLine();
            if (line == null) {
                continue;
            }
            RemotingCommand remotingCommand = new RemotingCommand();
            remotingCommand.setUid(line);
            ChannelFuture cf = ch.writeAndFlush(remotingCommand);
        }
    }
}

package com.github.zhangyinhao1234.study.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 16:22
 */
public class HelloClientStarup {

    String address = "127.0.0.1";

    Integer port = 9999;

    private void init() throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap handler = new Bootstrap();
        handler.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HelloClientInitializer());

        //连接服务器
        Channel ch = handler.connect(address, port).sync().channel();


        // 控制台输入
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (; ; ) {
            String line = in.readLine();
            if (line == null) {
                continue;
            }
            ch.writeAndFlush(line);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        new HelloClientStarup().init();
    }
}

package com.github.zhangyinhao1234.study.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 15:50
 */
public class HelloStartup {
    Integer port = 9999;

    private void init() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .localAddress(new InetSocketAddress(port))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HelloServerInitializer());
        serverBootstrap.bind().sync();

    }

    public static void main(String[] args) throws InterruptedException {
        HelloStartup helloStartup = new HelloStartup();
        helloStartup.init();
    }
}

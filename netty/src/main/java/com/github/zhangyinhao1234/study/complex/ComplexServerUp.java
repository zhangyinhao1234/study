package com.github.zhangyinhao1234.study.complex;

import com.github.zhangyinhao1234.study.protocol.RemotingCommand;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 21:17
 */
public class ComplexServerUp {

    int port = 9998;

    public static void main(String[] args) throws InterruptedException {
        ComplexServerUp complexServerUp = new ComplexServerUp();
        complexServerUp.start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this
                                .getClass().getClassLoader())), new ComplexServerHandler());
                    }
                });
        serverBootstrap.bind().sync();

    }
}

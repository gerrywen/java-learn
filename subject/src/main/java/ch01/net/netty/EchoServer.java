package ch01.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


/**
 * @author wenguoli
 * @description:
 * @date 2020/2/24 0024 20:48
 */
public final class EchoServer {
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));

    public static void main(String[] args) throws Exception{
        // Configure the server.
        // 创建EventLoopGroup   accept线程组 NioEventLoop
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建EventLoopGroup   I/O线程组
        NioEventLoopGroup workerGroup2 = new NioEventLoopGroup(1);
        try {
            // 服务端启动引导工具类
            ServerBootstrap b = new ServerBootstrap();
            // 配置服务端处理的reactor线程组以及服务端的其他配置
            b.group(bossGroup, workerGroup2).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new EchoServerHandler());
                }
            });

            // 通过bind启动服务
            ChannelFuture f = b.bind(PORT).sync();
            // 阻塞主线程，知道网络服务被关闭
            f.channel().closeFuture().sync();
        }finally {
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup2.shutdownGracefully();
        }

    }

}

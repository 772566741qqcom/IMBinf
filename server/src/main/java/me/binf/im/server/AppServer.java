package me.binf.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.binf.im.server.codec.ProtobufDecoder;
import me.binf.im.server.codec.ProtobufEncoder;
import me.binf.im.server.handler.AppServerHandler;
import me.binf.im.server.manager.LogTAGManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by wangbin on 2015/11/8.
 */
public class AppServer {

    private final Logger Log = LoggerFactory.getLogger(getClass());

    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;


    public AppServer(int workerNum) {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(workerNum);
    }


    public void startServer(int port){
        Log.info(LogTAGManager.SERVER_INFO + "info:正在启动Netty服务器,监听端口["
                + port + "]");
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();

                pipeline.addLast("MsgDecoder", new ProtobufDecoder());// 解码器
                pipeline.addLast("MsgEncoder", new ProtobufEncoder());// 编码器
                pipeline.addLast("handler", new AppServerHandler());// 消息处理器
            }
        });
        applyConnectionOptions(bootstrap);

        InetSocketAddress addr = new InetSocketAddress(port);

        bootstrap.bind(addr).addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future)
                    throws Exception {
                if (future.isSuccess()) {
                    Log.info(LogTAGManager.CLIENT_SERVER_INFO
                            + "info:Netty服务器,启动成功");
                } else {
                    Log.info(LogTAGManager.CLIENT_SERVER_INFO
                            + "info:Netty服务器,启动失败");
                }
            }
        });

    }


    /**
     * 停止服务器(一般用不到)
     */
    public void shoutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    /**
     * 配置连接属性
     * @param bootstrap
     *
     */
    protected void applyConnectionOptions(ServerBootstrap bootstrap) {

        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.childOption(ChannelOption.SO_LINGER, 0);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

    }

}

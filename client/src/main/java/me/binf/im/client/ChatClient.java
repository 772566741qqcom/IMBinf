package me.binf.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import me.binf.im.client.codec.ProtobufDecoder;
import me.binf.im.client.codec.ProtobufEncoder;
import me.binf.im.client.handler.SecureChatClientHandler;
import me.binf.im.protobuf.MessageProto.Message;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by wangbin on 2015/11/9.
 */
public class ChatClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "9090"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("MsgDecoder", new ProtobufDecoder());// 解码器
                            pipeline.addLast("MsgEncoder", new ProtobufEncoder());// 编码器
                            pipeline.addLast(new SecureChatClientHandler());
                        }
                    });

            // Start the connection attempt.
            Channel ch = b.connect(HOST, PORT).sync().channel();

            // Read commands from the stdin.
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                Message.Builder msg2 =  Message.newBuilder();
                msg2.setHeader(101);
                msg2.setContent("客户端说:"+line);
                Message msgSend = msg2.build();
                // Sends the received line to the server.
                lastWriteFuture = ch.writeAndFlush(msgSend);

                // If user typed the 'bye' command, wait until the server closes
                // the connection.
                if ("bye".equals(line.toLowerCase())) {
                    ch.closeFuture().sync();
                    break;
                }
            }

            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}

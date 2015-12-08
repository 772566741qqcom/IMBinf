package me.binf.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.binf.im.server.router.MessageRouter;
import me.binf.im.server.manager.LogTAGManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.binf.im.protobuf.MessageProto.Message;
/**
 * Created by wangbin on 2015/11/8.
 */
public class AppServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 创建日志对象
     */
    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        Message receiveMsg = (Message) msg;
        MessageRouter messageRouter =  new MessageRouter();
        Message sendMessage =  messageRouter.route(receiveMsg);
        ctx.channel().writeAndFlush(sendMessage);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.info(LogTAGManager.CLIENT_CONNECTED + "remoteAddress:"
                + ctx.channel().remoteAddress());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Log.info(LogTAGManager.CLIENT_DISCONNECTED
                + LogTAGManager.LOG_SEPARATE_PARAMS + "remoteAddress:"
                + ctx.channel().remoteAddress());


    }


}

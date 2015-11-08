package me.binf.im.server.msg.handler.impl;

import io.netty.channel.Channel;
import me.binf.im.protobuf.MessageProto;
import me.binf.im.server.msg.handler.AppMsgHandler;
import me.binf.im.protobuf.MessageProto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangbin on 2015/11/8.
 */
public class ChatHandler implements AppMsgHandler {

    private final static Logger Log = LoggerFactory.getLogger(ChatHandler.class);

    public void process(Channel channel,Message msg) {

        try {
            Message.Builder msg2 =  Message.newBuilder();
            msg2.setHeader(201);
            msg2.setContent("服务器说:"+msg.getContent());
            Message msgSend = msg2.build();
            channel.writeAndFlush(msgSend);
        }catch (Exception e){
            Log.error(e.getMessage(), e);
        }

    }
}

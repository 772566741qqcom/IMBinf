package me.binf.im.server;

import me.binf.im.protobuf.MessageProto.Message;
import me.binf.im.server.msg.handler.AppMsgHandler;
import me.binf.im.server.msg.handler.impl.ChatHandler;


/**
 * Created by wangbin on 2015/11/8.
 */
public class AppMsgHandlerFactory {

    public static AppMsgHandler getAppMsgHandler(Message msg) {

        int header = msg.getHeader();
        if (header == 101) {
            // 登录
            return new ChatHandler();
        } else {
            return null;
        }
    }

}

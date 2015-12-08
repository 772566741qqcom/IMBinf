package me.binf.im.server.router;

import me.binf.im.protobuf.MessageProto.*;
import me.binf.im.server.msg.handler.AppMsgHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbin on 2015/11/10.
 */
public class MessageRouterRule {

    private final MessageRouter routerBuilder;

    private Integer code;

    private List<AppMsgHandler> handlers = new ArrayList<>();


    public MessageRouterRule(MessageRouter routerBuilder) {
        this.routerBuilder = routerBuilder;
    }

    public MessageRouterRule code(Integer code){
        this.code = code;
        return this;
    }

    protected boolean test(Message message){

        return (this.code.equals(message.getHeader().getNumber()));
    }

    protected Message  service(Message message){
        Message res = null;

        for(AppMsgHandler appMsgHandler : this.handlers){
            res = appMsgHandler.handle(message);
        }

        return res;
    }




}

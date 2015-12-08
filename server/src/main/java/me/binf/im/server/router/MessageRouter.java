package me.binf.im.server.router;

import me.binf.im.protobuf.MessageProto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbin on 2015/11/10.
 */
public class MessageRouter {

    protected final Logger log = LoggerFactory.getLogger(MessageRouter.class);

    private final List<MessageRouterRule> rules = new ArrayList<>();


    public MessageRouterRule rule(){
        return new MessageRouterRule(this);
    }

    public Message route(final Message message){

        final List<MessageRouterRule> matchRules = new ArrayList<>();

        for(final MessageRouterRule rule : rules){
            if(rule.test(message)){
                matchRules.add(rule);
            }
        }
        if (matchRules.size() == 0) {
            return null;
        }
        Message res = null;

        for(MessageRouterRule rule : matchRules){
            res =  rule.service(message);
        }

        return res;
    }



}

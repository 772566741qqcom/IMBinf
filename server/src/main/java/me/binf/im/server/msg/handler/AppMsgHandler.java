package me.binf.im.server.msg.handler;

import me.binf.im.protobuf.MessageProto.Message;
import io.netty.channel.Channel;

/**
 * 消息处理器
 * 
 * @author jacklin
 * 
 */
public interface AppMsgHandler {

	/**
	 * 处理方法
	 * 
	 * @param channel
	 */
	public void process(Channel channel, Message msg);

	public Message handle(Message msg);

}
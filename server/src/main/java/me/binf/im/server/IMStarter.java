package me.binf.im.server;

import org.apache.log4j.Logger;

/**
 * Created by wangbin on 2015/11/8.
 */
public class IMStarter {

    private static Logger Log = Logger
            .getLogger(IMStarter.class.getName());

    public static void main(String[] args) {

        // 启动APP服务器
        new AppServer(200).startServer(Integer.valueOf(9090));


    }
}

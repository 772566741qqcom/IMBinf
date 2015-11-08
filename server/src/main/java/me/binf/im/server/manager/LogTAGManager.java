package me.binf.im.server.manager;

/**
 * Created by wangbin on 2015/11/8.
 */
public class LogTAGManager {

    public static final String LOG_SEPARATE = " - ";// 日志分隔符

    public static final String LOG_SEPARATE_PARAMS = "|";// 日志参数分隔符

    public static final String SERVER_INFO = "server_info" + LOG_SEPARATE;// 服务器信息

    // =============================客户端===========================================
    public static final String CLIENT_SERVER_INFO = "client_server_info"
            + LOG_SEPARATE;// 客户端服务器信息


    public static final String CLIENT_CONNECTED = "client_connected"
            + LOG_SEPARATE;// 客户端建立连接

    public static final String CLIENT_DISCONNECTED = "client_disconnected"
            + LOG_SEPARATE;// 客户端断开连接

    public static final String CLIENT_IDLE_TIMEOUT = "client_idle_timeout"
            + LOG_SEPARATE;// 客户端空闲超时

}

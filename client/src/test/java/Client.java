import me.binf.im.protobuf.Message;

import java.io.*;
import java.net.Socket;

/**
 * Created by wangbin on 2015/11/7.
 */
public class Client {


    public static void main(String[] args)throws Exception {

        Socket socket = new Socket("localhost",9090);

        InputStream ins = socket.getInputStream();

        OutputStream os = socket.getOutputStream();
        System.out.print("请输入: \t");
        BufferedReader sin =new BufferedReader(new InputStreamReader(System.in));

        String  readline = sin.readLine();
        while (!readline.equals("bye")) {

            byte[] outMsg =  str4bytes(readline);
            os.write(outMsg);
            os.flush();
            ins4Str(ins);
            readline = sin.readLine();
        }

        os.close();
        ins.close();
    }


    public static byte[] str4bytes(String str){
        Message.Msg.Builder outMsgBuilder = Message.Msg.newBuilder();
        outMsgBuilder.setUserId(4);
        outMsgBuilder.setContent(str);
        Message.Msg outMsg = outMsgBuilder.build();
        return outMsg.toByteArray();
    }

    public static void  ins4Str(InputStream ins){
        try {
            //服务器说
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] temp = new byte[1024];
            int size = 0;
            while ((size=ins.read(temp))!=-1){
                System.out.println("size:"+size);
                out.write(temp,0,size);
            }
            byte[] msgBytes = out.toByteArray();
            Message.Msg msg = Message.Msg.parseFrom(msgBytes);
            System.out.println(msg.getContent());
        }catch (Exception e){
             e.printStackTrace();
        }

    }



}

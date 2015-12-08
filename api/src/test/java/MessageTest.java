import me.binf.im.protobuf.MessageProto;
import me.binf.im.protobuf.MessageProto.Message;

/**
 * Created by wangbin on 2015/11/16.
 */
public class MessageTest {


    public static void main(String[] args) {

        Message.Builder messageBuilder = Message.newBuilder();

        messageBuilder.setHeader(MessageProto.MSG.Get_Friends_Request);
        messageBuilder.setSequence(1);

        Message message = messageBuilder.build();

        System.out.println(message.getHeader().name());
    }
}

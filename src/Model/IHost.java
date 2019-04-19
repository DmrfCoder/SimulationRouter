package Model;

import Bean.Message;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public interface IHost {
    boolean outputMessage();

    boolean outputMessageProxy();

    boolean inputMessage(Message message);

    ArrayList<Message> buildMessages(int messageCount);

    void startSendMessage();

    void stopSendMessage();

}

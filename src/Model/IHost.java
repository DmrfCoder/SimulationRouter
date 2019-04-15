package Model;

import Bean.Message;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public interface IHost {
    void sendMessage(int  messageCount);

    void receiveMessage(Message message);

    ArrayList<Message> buildMessages(int messageCount);

}

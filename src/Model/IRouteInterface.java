package Model;

import Bean.Message;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public interface IRouteInterface {

    void receiveMessage(Message message);

    boolean sendMessage(Message message);


    class ReceivingMemory {

    }
}

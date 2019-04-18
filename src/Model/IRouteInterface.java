package Model;

import Bean.Message;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public interface IRouteInterface {

    boolean inputMessage(Message message);

    boolean outputMessage(Message message);

    void setUpdatePercentageListener(UpdatePercentageListener updatePercentageListener);

    interface UpdatePercentageListener {
        void updatePercentage(String hostIp,float percentage, int messageCount);
    }

    interface UpdateInputMessageMomentListener{
        void inputMessageMoment(Message message,boolean flag);
    }

}

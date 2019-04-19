package Model;

import Bean.Memory;
import Bean.Message;
import Model.impl.Host;
import Model.impl.RouterInterface;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public interface IRouter {

    RouterInterface registHost(Host host);

    void readAndHandleMemory();

    void readAndHandleMemoryProxt();

    boolean sendMessageToHost(Message message);

    void startReadMemoryTask();

    void stopReadMemoryTask();


}

package Model.impl;

import Bean.Memory;
import Bean.Message;
import Model.IRouteInterface;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class RouterInterface implements IRouteInterface {
    private Host host;

    private Memory memory;

    public RouterInterface(Host host) {
        this.host = host;
        memory = new Memory(2);
    }

    @Override
    public void receiveMessage(Message message) {

    }

    @Override
    public boolean sendMessage(Message message) {

        return memory.addContentToMemory(message);
    }
}

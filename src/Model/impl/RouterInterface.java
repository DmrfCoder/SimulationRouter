package Model.impl;

import Bean.Memory;
import Bean.Message;
import Model.IRouteInterface;
import com.sun.istack.internal.NotNull;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class RouterInterface implements IRouteInterface {
    private Host host;

    private Memory memory;

    private int port;

    private UpdatePercentageListener updatePercentageListener;

    private UpdateInputMessageMomentListener updateInputMessageMomentListener;

    public Memory getMemory() {
        return memory;
    }

    public Host getHost() {
        return host;
    }

    public RouterInterface(Host host, int port, UpdateInputMessageMomentListener updateInputMessageMomentListener, int memorySize) {
        this.host = host;
        this.port = port;
        memory = new Memory(2);
        this.updateInputMessageMomentListener = updateInputMessageMomentListener;

    }

    public RouterInterface(Host host, int port, UpdateInputMessageMomentListener updateInputMessageMomentListener) {
        this.host = host;
        this.port = port;
        memory = new Memory(2);
        this.updateInputMessageMomentListener = updateInputMessageMomentListener;

    }


    public int getPort() {
        return port;
    }

    @Override
    public boolean inputMessage(Message message) {

        float percentage = memory.getMemoryPercentage();
        if (updatePercentageListener != null) {
            updatePercentageListener.updatePercentage(host.getIp(), percentage, memory.getMemoryCurCount());
        }


        if ( memory.addContentToMemory(message)){
            updateInputMessageMomentListener.inputMessageMoment(message,true);
            return true;
        }else {
            updateInputMessageMomentListener.inputMessageMoment(message,false);
            return false;
        }

    }

    @Override
    public boolean outputMessage(Message message) {
        float percentage = memory.getMemoryPercentage();
        if (percentage > 1) {
            percentage = 1;
        }
        updatePercentageListener.updatePercentage(host.getIp(), percentage, memory.getMemoryCurCount());
        return host.inputMessage(message);
    }

    @Override
    public void setUpdatePercentageListener(UpdatePercentageListener updatePercentageListener) {
        this.updatePercentageListener = updatePercentageListener;
    }
}

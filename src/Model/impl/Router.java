package Model.impl;

import Bean.Memory;
import Bean.Message;
import Bean.RouteTable;
import Listener.RouterAndHostMomentStrListener;
import Model.IRouteInterface;
import Model.IRouter;
import Util.PrintUtil;
import Util.RandomUtil;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Router implements IRouter, IRouteInterface.UpdateInputMessageMomentListener {
    private ArrayList<Integer> hasUsedPorts;

    private ArrayList<RouterInterface> routerInterfaceList;

    private ScheduledExecutorService scheduledExecutorService;

    private int routerId;


    private RouterAndHostMomentStrListener routerAndHostMomentStrListener;

    private RouteTable routeTable;

    public Router(int routerId) {
        this.routerId = routerId;
        routeTable = new RouteTable();
        routerInterfaceList = new ArrayList<>();
        hasUsedPorts = new ArrayList<>();

    }

    public void setRouterAndHostMomentStrListener(RouterAndHostMomentStrListener routerAndHostMomentStrListener) {
        this.routerAndHostMomentStrListener = routerAndHostMomentStrListener;
    }

    @Override
    public RouterInterface registHost(Host host) {
        boolean hasExit = false;
        for (RouterInterface routerInterface : routerInterfaceList) {
            if (routerInterface.getHost().equals(host)) {
                hasExit = true;
                break;
            }
        }

        if (!hasExit) {
            int port = RandomUtil.getInstance().getRandomPort();
            while (hasUsedPorts.contains(port)) {
                port = RandomUtil.getInstance().getRandomPort();
            }
            hasUsedPorts.add(port);
            RouterInterface routerInterface = new RouterInterface(host, port, this);
            routerInterfaceList.add(routerInterface);
            return routerInterface;
        }
        return null;

    }

    @Override
    public void readAndHandleMemory() {
        for (RouterInterface routerInterface : routerInterfaceList) {
            Memory memory = routerInterface.getMemory();
            Queue<Message> messageQueue = memory.getMessageQueue();
            while (!messageQueue.isEmpty()) {
                Message message = messageQueue.peek();
                if (sendMessageToHost(message)) {
                    memory.removeMessageFromMemory(message);
                    messageQueue.poll();
                }
            }
        }
    }

    @Override
    public boolean sendMessageToHost(Message message) {

        String targetIp = message.getTargetAddress();
        for (RouterInterface routerInterface : routerInterfaceList) {
            if (routerInterface.getHost().getIp().equals(targetIp)) {
                routerAndHostMomentStrListener.addMomentStrToRouterQueue(routerId, "发送来自主机：" + message.getOriginIp() + " 的报文到主机： " + message.getTargetAddress(), 0);
                routerInterface.outputMessage(message);
                return true;
            }
        }
        return false;
    }

    @Override
    public void startReadMemoryTask() {
        scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleWithFixedDelay(this::readAndHandleMemory, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void stopReadMemoryTask() {
        scheduledExecutorService.shutdown();
    }


    @Override
    public void inputMessageMoment(Message message) {
        routerAndHostMomentStrListener.addMomentStrToRouterQueue(routerId, "接收到来自主机：" + message.getOriginIp() + "的报文", 1);

    }
}

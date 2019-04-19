package Model.impl;

import Bean.Message;
import Configure.RouterAndHostConfigure;
import Listener.RouterAndHostMomentStrListener;
import Model.IHost;
import Util.PrintUtil;
import Util.RandomUtil;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Host implements IHost {
    private String ip;

    private ArrayList<String> ips;

    private int sentCount;

    private RouterInterface routerInterface;

    private int processId;


    private ScheduledExecutorService scheduledExecutorService;

    private RouterAndHostMomentStrListener routerAndHostMomentStrListener;


    public void setRouterInterface(RouterInterface routerInterface) {
        this.routerInterface = routerInterface;

    }

    public RouterInterface getRouterInterface() {
        return routerInterface;
    }

    public String getIp() {
        return ip;
    }

    public void setIps(ArrayList<String> ips) {
        ips.remove(ip);
        this.ips = ips;
    }

    public Host(String ip) {
        PrintUtil.printLn("初始化主机：" + ip);
        this.ip = ip;
        sentCount = 0;

        RandomUtil.getInstance().addIpToIps(ip);
    }

    public void setRouterAndHostMomentStrListener(RouterAndHostMomentStrListener routerAndHostMomentStrListener) {
        this.routerAndHostMomentStrListener = routerAndHostMomentStrListener;
    }

    @Override
    public boolean outputMessage() {


        try {
            ArrayList<Message> messageArrayList = buildMessages(RouterAndHostConfigure.messageCountSentPerSecondOfHost);
            for (Message message : messageArrayList) {

                if (routerInterface.inputMessage(message)) {
                    routerAndHostMomentStrListener.addMomentStrToHostQueue(ip, "发送报文到主机：" + message.getTargetAddress(), 1);
                    //发送成功
                } else {
                    //发送失败，因为路由器存储器容量不够了
                    routerAndHostMomentStrListener.addMomentStrToHostQueue(ip, "发送报文到主机：" + message.getTargetAddress() + "失败!", 2);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception-outputMessage:" + e.getLocalizedMessage());
        }


        return true;
    }

    @Override
    public boolean outputMessageProxy() {
        boolean res = false;
        try {
            res = outputMessage();
        } catch (Exception e) {
            PrintUtil.printLn("Exception-outputMessageProxy:" + e.getLocalizedMessage());
        }

        return res;
    }


    @Override
    public boolean inputMessage(Message message) {
        routerAndHostMomentStrListener.addMomentStrToHostQueue(ip, "收到来自主机：" + message.getOriginIp() + "的报文", 0);
        return true;
    }

    @Override
    public ArrayList<Message> buildMessages(int messageCount) {
        ArrayList<Message> messageArrayList = new ArrayList<>();

        RandomUtil randomUtil = RandomUtil.getInstance();

        for (int index = 0; index < messageCount; index++) {

            Message message = new Message(processId, randomUtil.getRandomPort(), sentCount++, ip, randomUtil.getRandomIpForSendMessage(ip), randomUtil.getRandomMessageContent());

            messageArrayList.add(message);

        }
        return messageArrayList;
    }

    @Override
    public void startSendMessage() {

        scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleWithFixedDelay(this::outputMessageProxy, 0, RouterAndHostConfigure.periodOfHostSendMessage, TimeUnit.MICROSECONDS);
        PrintUtil.printLn("启动主机" + ip + "的发送信息线程");
    }

    @Override
    public void stopSendMessage() {
        scheduledExecutorService.shutdown();
        PrintUtil.printLn("停止主机" + ip + "的发送信息线程");

    }


}

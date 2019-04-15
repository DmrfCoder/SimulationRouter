package Model.impl;

import Bean.Message;
import Model.IHost;
import Util.RandomUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Host implements IHost {
    private String ip;
    private RouterInterface routerInterface;
    private int processId;

    private Set<String> ips;

    private int sentCount;

    public String getIp() {
        return ip;
    }

    public void setIps(Set<String> ips) {
        ips.remove(ip);
        this.ips = ips;
    }

    public Host(String ip, RouterInterface routerInterface) {
        this.ip = ip;
        this.routerInterface = routerInterface;
        sentCount = 0;

    }


    @Override
    public void sendMessage(int messageCount) {

        ArrayList<Message> messageArrayList = buildMessages(messageCount);
        for (Message message : messageArrayList) {

            if (routerInterface.sendMessage(message)) {
                //发送成功
            } else {
                //发送失败，因为路由器存储器容量不够了
            }
        }

    }

    @Override
    public void receiveMessage(Message message) {

    }

    @Override
    public ArrayList<Message> buildMessages(int messageCount) {
        ArrayList<Message> messageArrayList = new ArrayList<>();

        RandomUtil randomUtil = RandomUtil.getInstance();

        for (int index = 0; index < messageCount; index++) {

            Message message = new Message(processId, randomUtil.getRandomPort(), sentCount++, randomUtil.getRandomIp(ip), randomUtil.getRandomMessageContent());

            messageArrayList.add(message);

        }
        return messageArrayList;
    }
}

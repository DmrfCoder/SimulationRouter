package Model.impl;

import Model.IMain;
import Util.RandomUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Main implements IMain {
    @Override
    public void startProcess(int routeCount, int hostCount) {
        Router[] routers = new Router[routeCount];
        Host[] hosts = new Host[hostCount];

        for (int index = 0; index < routeCount; index++) {
            routers[index] = new Router();
        }


        Set<String> ips = new HashSet<>();
        for (int index = 0; index < hostCount; index++) {
            String ip = RandomUtil.getInstance().buildRandomIp(index);
            hosts[index] = new Host(ip, routers[0]);
            ips.add(ip);
        }

        for (int index = 0; index < hostCount; index++) {
            hosts[index].setIps(ips);
        }

        RandomUtil.getInstance().setIps(ips);

        for (Host host : hosts) {
            routers[0].registHost(host);
        }
    }


}

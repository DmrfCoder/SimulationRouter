package Model.impl;

import Listener.RouterAndHostMomentStrListener;
import Model.IMainModel;
import Model.IRouteInterface;
import Presenter.IMainPresenter;
import Util.RandomUtil;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class MainModel implements IMainModel, RouterAndHostMomentStrListener, IRouteInterface.UpdatePercentageListener {
    private Router[] routers;
    private Host[] hosts;


    private AddMomentToRouterAndHostListener addMomentToRouterAndHostListener;

    private IMainPresenter.UpdatePercentageListener updatePercentageListener;


    public MainModel(AddMomentToRouterAndHostListener addMomentToRouterAndHostListener, IMainPresenter.UpdatePercentageListener updatePercentageListener) {
        this.addMomentToRouterAndHostListener = addMomentToRouterAndHostListener;
        this.updatePercentageListener = updatePercentageListener;
    }

    @Override
    public void initRoutersAndHosts(int routeCount, int hostCount, InitRoutersAndHostsListener initRoutersAndHostsListener) {
        routers = new Router[routeCount];
        hosts = new Host[hostCount];


        for (int index = 0; index < routeCount; index++) {
            routers[index] = new Router(index);
            routers[index].setRouterAndHostMomentStrListener(this);

        }

        ArrayList<String> ips = new ArrayList<>();

        for (int index = 0; index < hostCount; index++) {
            String ip = RandomUtil.getInstance().buildRandomIp(index);
            hosts[index] = new Host(ip);
            hosts[index].setRouterAndHostMomentStrListener(this);
            ips.add(ip);

        }


        for (int index = 0; index < hostCount; index++) {
            hosts[index].setIps(ips);
        }


        ArrayList<Integer> ports = new ArrayList<>();
        ArrayList<String> infos = new ArrayList<>();
        for (Host host : hosts) {
            RouterInterface routerInterface = routers[0].registHost(host);
            routerInterface.setUpdatePercentageListener(this);

            host.setRouterInterface(routerInterface);
            ports.add(routerInterface.getPort());
            infos.add(host.getIp());
            infos.add(String.valueOf(routerInterface.getPort()));

        }
        initRoutersAndHostsListener.initHostsSuccess(infos);

        initRoutersAndHostsListener.initRouterSuccess(ports);

    }

    @Override
    public void startHost(String hostIp, StartAndStopListener startAndStopListener) {
    }

    @Override
    public void startRouter(int routerId, StartAndStopListener startAndStopListener) {
    }

    @Override
    public void stopHost(String hostIp, StartAndStopListener startAndStopListener) {
    }

    @Override
    public void stopRouter(int routerId, StartAndStopListener startAndStopListener) {
    }

    @Override
    public void startAll(StartAndStopListener startAndStopListener) {
        for (Host host : hosts) {
            host.startSendMessage();
        }

        for (Router router : routers) {
            router.startReadMemoryTask();
        }
        startAndStopListener.startAllSuccess();

    }

    @Override
    public void stopAll(StartAndStopListener startAndStopListener) {
        for (Host host : hosts) {
            host.stopSendMessage();
        }

        for (Router router : routers) {
            router.stopReadMemoryTask();
        }


        startAndStopListener.stopAllSuccess();
    }


    @Override
    public void addMomentStrToRouterQueue(int id, String momentInfo, int type) {
        addMomentToRouterAndHostListener.addToRouter(id, momentInfo, type);
    }

    @Override
    public void addMomentStrToHostQueue(String ip, String momentInfo, int type) {
        for (int index = 0; index < hosts.length; index++) {
            if (hosts[index].getIp().equals(ip)) {
                addMomentToRouterAndHostListener.addToHost(index, momentInfo, type);
            }
        }


    }

    @Override
    public void updatePercentage(String hostIp, float percentage, int messageCount) {

        for (int index = 0; index < hosts.length; index++) {
            if (hosts[index].getIp().equals(hostIp)) {
                updatePercentageListener.updatePercentage(index, percentage, messageCount);
                break;
            }
        }
    }
}

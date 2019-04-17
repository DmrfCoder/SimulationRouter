package Model;

import Listener.RouterAndHostMomentStrListener;
import Presenter.IMainPresenter;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public interface IMainModel {

    interface InitRoutersAndHostsListener {
        void initRouterSuccess(ArrayList<Integer> ports);

        void initHostsSuccess(ArrayList<String> hostsIp);
    }

    void initRoutersAndHosts(int routeCount, int hostCount, InitRoutersAndHostsListener initRoutersAndHostsListener);


    interface StartAndStopListener {
        void startHostSuccess(String ip);

        void startRouterSuccess(int routerId);

        void stopHostSuccess(String ip);

        void stopRouterSuccess(String routerId);

        void startAllSuccess();

        void stopAllSuccess();

    }

    void startHost(String hostIp,StartAndStopListener startAndStopListener);

    void startRouter(int routerId,StartAndStopListener startAndStopListener);

    void stopHost(String hostIp,StartAndStopListener startAndStopListener);

    void stopRouter(int routerId,StartAndStopListener startAndStopListener);

    void startAll(StartAndStopListener startAndStopListener);

    void stopAll(StartAndStopListener startAndStopListener);


    interface AddMomentToRouterAndHostListener {
        void addToRouter(int index, String content,int type);

        void addToHost(int index, String content,int type);
    }




}

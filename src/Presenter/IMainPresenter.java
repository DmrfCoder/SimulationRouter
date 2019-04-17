package Presenter;

import Listener.RouterAndHostMomentStrListener;
import Model.IMainModel;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public interface IMainPresenter {


    void initRoutersAndHosts(int routeCount, int hostCount, IMainModel.InitRoutersAndHostsListener initRoutersAndHostsListener);


    void startHost(String hostIp);

    void startRouter(int routerId);

    void stopHost(String hostIp);

    void stopRouter(int routerId);

    void startAll();

    void stopAll();



    interface UpdatePercentageListener {
        void updatePercentage(int index, float percentage);
    }
}

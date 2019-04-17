package Presenter;

import Listener.RouterAndHostMomentStrListener;
import Model.IMainModel;
import Model.impl.MainModel;
import View.IMainView;
import View.MainView;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public class MainPresenter implements IMainPresenter, IMainModel.StartAndStopListener, IMainModel.AddMomentToRouterAndHostListener, IMainPresenter.UpdatePercentageListener {

    private IMainView iMainView;
    private IMainModel iMainModel;

    private IMainModel.AddMomentToRouterAndHostListener addMomentToRouterAndHostListener;


    public MainPresenter(IMainView iMainView, IMainModel.AddMomentToRouterAndHostListener addMomentToRouterAndHostListener) {
        this.addMomentToRouterAndHostListener = addMomentToRouterAndHostListener;
        this.iMainView=iMainView;
        iMainModel = new MainModel(this, this);

    }


    @Override
    public void initRoutersAndHosts(int routeCount, int hostCount, IMainModel.InitRoutersAndHostsListener initRoutersAndHostsListener) {
        iMainModel.initRoutersAndHosts(routeCount, hostCount, initRoutersAndHostsListener);
    }

    @Override
    public void startHost(String hostIp) {
        iMainModel.startHost(hostIp, this);
    }

    @Override
    public void startRouter(int routerId) {
        iMainModel.startRouter(routerId, this);
    }

    @Override
    public void stopHost(String hostIp) {
        iMainModel.stopHost(hostIp, this);
    }

    @Override
    public void stopRouter(int routerId) {
        iMainModel.stopRouter(routerId, this);
    }

    @Override
    public void startAll() {
        iMainModel.startAll(this);
    }

    @Override
    public void stopAll() {
        iMainModel.stopAll(this);
    }


    @Override
    public void startHostSuccess(String ip) {

    }

    @Override
    public void startRouterSuccess(int routerId) {

    }

    @Override
    public void stopHostSuccess(String ip) {

    }

    @Override
    public void stopRouterSuccess(String routerId) {

    }

    @Override
    public void startAllSuccess() {

    }

    @Override
    public void stopAllSuccess() {

    }

    @Override
    public void addToRouter(int index, String content,int type) {

        addMomentToRouterAndHostListener.addToRouter(index, content,type);
    }

    @Override
    public void addToHost(int index, String content,int type) {
        addMomentToRouterAndHostListener.addToHost(index, content,type);

    }

    @Override
    public void updatePercentage(int index, float percentage) {
        iMainView.updatePercentage(index, percentage);
    }
}

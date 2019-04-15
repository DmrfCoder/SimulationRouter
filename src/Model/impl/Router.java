package Model.impl;

import Bean.Message;
import Bean.RouteTable;
import Model.IRouter;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Router implements IRouter {
    private int receiveInterfaceCount;
    private int sendInterfaceCount;

    private ArrayList<RouterInterface> receiveRouterInterfaceList;
    private ArrayList<RouterInterface> sendRouterInterfaceList;

    private RouteTable routeTable;

    public Router() {
        routeTable=new RouteTable();
    }

    @Override
    public void registHost(Host host) {


    }

}

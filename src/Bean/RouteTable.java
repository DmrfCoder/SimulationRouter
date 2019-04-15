package Bean;

import Model.impl.Host;
import Model.impl.RouterInterface;

import java.util.Map;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public class RouteTable {
    private Map<String, RouterInterface> routeTable;

    public void registHost(Host host) {
        routeTable.put(host.getIp(), new RouterInterface(host));
    }

}

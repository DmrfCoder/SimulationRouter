package Listener;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public interface RouterAndHostMomentStrListener {
    void addMomentStrToRouterQueue(int id, String momentInfo, int type);

    void addMomentStrToHostQueue(String index, String momentInfo, int type);
}

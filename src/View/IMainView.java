package View;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-16
 */
public interface IMainView {
    void initView();
    void initMemoryCircleViews(ArrayList<Integer> ports);
    void initMomentsViews(ArrayList<String> hostsIp);
    void updatePercentage(int index, double percentage, int messageCount);



}

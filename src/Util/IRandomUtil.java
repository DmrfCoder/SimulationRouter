package Util;

import Bean.Moment;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public interface IRandomUtil {
    String buildRandomIp(int seed);

    int getRandomPort();

    String getRandomIpForSendMessage(String selfIp);

    int randInt(int min, int max);

    String getRandomMessageContent();

    void addIpToIps(String ip);

    Moment buildRandomMoment(int seed);

    Moment buildRandomMoment();

}

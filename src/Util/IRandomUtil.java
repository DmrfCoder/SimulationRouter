package Util;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public interface IRandomUtil {
    String buildRandomIp(int seed);

    int getRandomPort();

    String getRandomIp(String selfIp);

    int randInt(int min, int max);

    String getRandomMessageContent();

}

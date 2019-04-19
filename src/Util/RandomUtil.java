package Util;

import Bean.Moment;
import Configure.RouterAndHostConfigure;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class RandomUtil implements IRandomUtil {


    private static RandomUtil randomUtil;

    private ArrayList<String> ips;

    private RandomUtil() {
        this.ips = new ArrayList<>();

    }

    public static RandomUtil getInstance() {
        if (randomUtil == null) {
            synchronized (RandomUtil.class) {
                if (randomUtil == null) {
                    randomUtil = new RandomUtil();
                }
            }
        }
        return randomUtil;
    }

    public void setIps(Set<String> ips) {
        for (String ip : ips) {
            this.ips.add(ip);
        }
    }

    @Override
    public String buildRandomIp(int seed) {
        int[][] range = {{607649792, 608174079},
                // 61.232.0.0-61.237.255.255
                {1038614528, 1039007743},
                // 106.80.0.0-106.95.255.255
                {1783627776, 1784676351},
                // 121.76.0.0-121.77.255.255
                {2035023872, 2035154943},
                // 123.232.0.0-123.235.255.255
                {2078801920, 2079064063},
                // 139.196.0.0-139.215.255.255
                {-1950089216, -1948778497},
                // 171.8.0.0-171.15.255.255
                {-1425539072, -1425014785},
                // 182.80.0.0-182.92.255.255
                {-1236271104, -1235419137},
                // 210.25.0.0-210.47.255.255
                {-770113536, -768606209},
                // 222.16.0.0-222.95.255.255
                {-569376768, -564133889},
        };

        Random rdint = new Random(seed);
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }


    /**
     * 将十进制转换成IP地址
     *
     * @param ip
     * @return
     */
    private String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";
        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

    @Override
    public int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    @Override
    public String getRandomMessageContent() {
        int length = randInt(RouterAndHostConfigure.minMessageLength, RouterAndHostConfigure.minMessageLength + 100);
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public void addIpToIps(String ip) {
        if (ips == null) {
            ips = new ArrayList<>();
        }
        if (!ips.contains(ip)) {
            ips.add(ip);
        }

    }

    @Override
    public Moment buildRandomMoment(int seed) {
        /**
         * router：
         *         "接收到来自主机：ip 的报文"
         *         "发送报文到主机：ip "
         * host:
         *          "接收到来自主机：ip 的报文"
         *          "发送报文到主机：ip"
         */

        String content = "";
        int randI = randInt(0, 1);
        String ip = buildRandomIp(seed);
        if (randI == 0) {
            content = content + "接收来自主机：" + ip + "的报文";
        } else {
            content = content + "发送报文到主机：" + ip;
        }


        Moment moment = new Moment(content, 0);

        return moment;
    }

    @Override
    public Moment buildRandomMoment() {
        return buildRandomMoment(randInt(0, 10000));
    }


    @Override
    public int getRandomPort() {

        return randInt(1, 65535);
    }

    @Override
    public String getRandomIpForSendMessage(String selfIp) {
        if (ips != null) {
            int index = randInt(0, ips.size() - 1);
            if (ips.get(index).equals(selfIp)) {
                if (index + 1 < ips.size()) {
                    return ips.get(index + 1);
                } else if (index - 1 > 0) {
                    return ips.get(index - 1);
                } else {
                    return null;
                }
            } else {
                return ips.get(index);
            }
        }
        return null;
    }
}

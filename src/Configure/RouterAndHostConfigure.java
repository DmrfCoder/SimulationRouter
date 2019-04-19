package Configure;

/**
 * @author dmrfcoder
 * @date 2019-04-18
 */
public class RouterAndHostConfigure {
    public static int routerCount=1;
    public static int hostCOunt=4;
    public static int messageCountSentPerSecondOfHost =10;
    public static int messageCountReadPerSecondOfRouter=40;

    /**
     * /单位：微妙=100秒
     */
    public static long periodOfRouterReadMemory=1;
    public static long periodOfHostSendMessage=1;



    public static double routerInterfaceMemorySize=0.5;

    private static double minMessageSize=0.001;
    public static int minMessageLength= (int) (minMessageSize*1024*1024);


}

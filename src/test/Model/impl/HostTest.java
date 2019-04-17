package test.Model.impl;

import Model.impl.Host;
import Util.RandomUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Host Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 16, 2019</pre>
 */
public class HostTest {

    private Host host;

    @Before
    public void before() throws Exception {
        host = new Host(RandomUtil.getInstance().buildRandomIp(0));
    }

    @After
    public void after() throws Exception {
        //host.stopSendMessage();
    }

    /**
     * Method: setRouterInterface(RouterInterface routerInterface)
     */
    @Test
    public void testSetRouterInterface() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getIp()
     */
    @Test
    public void testGetIp() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setIps(Set<String> ips)
     */
    @Test
    public void testSetIps() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: sendMessage(int messageCount)
     */
    @Test
    public void testSendMessage() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: receiveMessage(Message message)
     */
    @Test
    public void testReceiveMessage() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: buildMessages(int messageCount)
     */
    @Test
    public void testBuildMessages() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: startSendMessage(int messageCount)
     */
    @Test
    public void testStartSendMessage() throws Exception {
        host.startSendMessage(10);
    }

    /**
     * Method: run()
     */
    @Test
    public void testRun() throws Exception {
//TODO: Test goes here... 
    }


} 

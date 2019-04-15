package test.Model.impl;

import Model.impl.Main;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Main Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 15, 2019</pre>
 */
public class MainTest {

    private Main main;

    @Before
    public void before() throws Exception {
        main=new Main();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: startProcess(int routeCount, int hostCount)
     */
    @Test
    public void testStartProcess() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRandomIp(int seed)
     */
    @Test
    public void testGetRandomIp() throws Exception {
        System.out.println(main.getRandomIp(0));
        System.out.println(main.getRandomIp(1));
        System.out.println(main.getRandomIp(2));
        System.out.println(main.getRandomIp(3));
        System.out.println(main.getRandomIp(4));
    }


    /**
     * Method: num2ip(int ip)
     */
    @Test
    public void testNum2ip() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = Main.getClass().getMethod("num2ip", int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 

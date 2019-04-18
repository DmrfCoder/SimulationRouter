package View;

import Bean.Moment;
import Configure.ViewConfigure;
import Model.IMainModel;
import Presenter.IMainPresenter;
import Presenter.MainPresenter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-16
 */
public class MainView extends JFrame implements IMainView, SwichButton.StateChangeListener, IMainModel.InitRoutersAndHostsListener, IMainModel.AddMomentToRouterAndHostListener {

    private JLabel memoryViewsTitleLable;
    private JLabel hostsMomentsViewsTitleLable;
    private JLabel routerMomentsViewsTitleLable;


    private ArrayList<MemoryCircleView> memoryCircleViews;

    private ArrayList<MomentsView> momentsViews;

    private MomentsView momentsViewRouter;


    private int screenHeight;
    //1440
    private int screenWidth;


    private IMainPresenter iMainPresenter;


    private SwichButton startAndStopButton;


    public MainView() throws HeadlessException {
        super();

        try {
            UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initView();

        iMainPresenter = new MainPresenter(this, this);

        iMainPresenter.initRoutersAndHosts(1, 4, this);
        setVisible(true);
    }

    @Override
    public void initView() {
        setTitle("路由器模拟程序");
        setSize(ViewConfigure.WindowWidth, ViewConfigure.WindowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);


        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();

        //900
        screenHeight = dimension.height;
        //1440
        screenWidth = dimension.width;

        int frm_Height = this.getHeight();
        int frm_width = this.getWidth();
        setLocation((screenWidth - frm_width) / 2,
                (screenHeight - frm_Height) / 2);

        enableOSXFullscreen(this);
        requestOSXFullscreen(this);


        getContentPane().setLayout(null);
        setDiyBackGround();
        JPanel jPanel = (JPanel) getContentPane();
        jPanel.setOpaque(false);

        startAndStopButton = new SwichButton(40);
        startAndStopButton.setBounds(screenWidth - 100, 5, 40, 40);
        startAndStopButton.setStateChangeListener(this);
        add(startAndStopButton);


    }

    @Override
    public void initMemoryCircleViews(ArrayList<Integer> ports) {

        memoryCircleViews = new ArrayList<MemoryCircleView>();
        int memoryCircleViewWidth;
        int memoryCircleViewHeight;
        int memoryCirclesStartX;
        int memoryCirclesStartY;
        int memoryCirclesDistance = 5;


        memoryCirclesStartX = memoryCirclesDistance;
        memoryCirclesStartY = startAndStopButton.getY() + startAndStopButton.getHeight() + memoryCirclesDistance;

        memoryViewsTitleLable = new JLabel("路由器各个接口中存储器的占用情况");
        memoryViewsTitleLable.setForeground(ViewConfigure.defaultTextColor);


        MemoryCircleView memoryCircleView1 = new MemoryCircleView(ports.get(0), 1);
        memoryCircleViewWidth = memoryCircleView1.getWindowWidth();
        memoryCircleViewHeight = memoryCircleView1.getWindowHeight();

        memoryViewsTitleLable.setBounds(5, 10, 250, 20);
        add(memoryViewsTitleLable);


        memoryCircleView1.setBounds(memoryCirclesStartX, memoryCirclesStartY, memoryCircleViewWidth, memoryCircleViewHeight);
        add(memoryCircleView1);
        memoryCircleViews.add(memoryCircleView1);


        MemoryCircleView memoryCircleView2 = new MemoryCircleView(ports.get(1), 2);
        memoryCircleView2.setBounds(memoryCirclesStartX, memoryCirclesStartY + memoryCircleViewHeight + memoryCirclesDistance, memoryCircleViewWidth, memoryCircleViewHeight);
        add(memoryCircleView2);
        memoryCircleViews.add(memoryCircleView2);

        MemoryCircleView memoryCircleView3 = new MemoryCircleView(ports.get(2), 3);
        memoryCircleView3.setBounds(memoryCirclesStartX, memoryCirclesStartY + (memoryCircleViewHeight + memoryCirclesDistance) * 2, memoryCircleViewWidth, memoryCircleViewHeight);
        add(memoryCircleView3);
        memoryCircleViews.add(memoryCircleView3);


        MemoryCircleView memoryCircleView4 = new MemoryCircleView(ports.get(3), 4);
        memoryCircleView4.setBounds(memoryCirclesStartX, memoryCirclesStartY + (memoryCircleViewHeight + memoryCirclesDistance) * 3, memoryCircleViewWidth, memoryCircleViewHeight);
        add(memoryCircleView4);
        memoryCircleViews.add(memoryCircleView4);


    }

    @Override
    public void initMomentsViews(ArrayList<String> hostsIp) {


        hostsMomentsViewsTitleLable = new JLabel("主机实时动态");
        hostsMomentsViewsTitleLable.setForeground(ViewConfigure.defaultTextColor);


        int momentsViewWidth;
        int momentsViewHeight;


        momentsViews = new ArrayList<MomentsView>();

        MomentsView momentsView1 = new MomentsView(1, hostsIp.get(0), hostsIp.get(1), 1);
        momentsViewWidth = momentsView1.getWindowWidth();
        momentsViewHeight = momentsView1.getWindowHeight();
        int momentsViewsDistance = 5;
        int momentStartX = screenWidth - momentsViewsDistance - momentsViewWidth - momentsViewsDistance - momentsViewWidth - momentsViewsDistance - momentsViewWidth;
        int momentStartY;
        momentStartY = startAndStopButton.getY() + startAndStopButton.getHeight() + momentsViewsDistance;


        routerMomentsViewsTitleLable = new JLabel("路由器实时动态");
        routerMomentsViewsTitleLable.setForeground(ViewConfigure.defaultTextColor);


        momentsViewRouter = new MomentsView(1, "路由器", 0, 40);

        routerMomentsViewsTitleLable.setBounds(momentStartX + momentsViewWidth / 2 - 78 / 2, 10, 250, 20);
        add(routerMomentsViewsTitleLable);

        momentsViewRouter.setBounds(momentStartX, momentStartY, momentsViewWidth, momentsViewRouter.getWindowHeight());
        add(momentsViewRouter);


        hostsMomentsViewsTitleLable.setBounds(momentStartX + momentsViewWidth + momentsViewsDistance + (momentsViewWidth * 2 + momentsViewsDistance) / 2 - 78 / 2, 10, 250, 20);
        add(hostsMomentsViewsTitleLable);


        //momentStartY = (screenHeight - momentsViewHeight * 2 - momentsViewsDistance - startAndStopButton.getY() - startAndStopButton.getHeight()) / 2;


        momentsView1.setBounds(momentStartX + momentsViewWidth + momentsViewsDistance, momentStartY, momentsViewWidth, momentsViewHeight);
        add(momentsView1);
        momentsViews.add(momentsView1);

        MomentsView momentsView2 = new MomentsView(2, hostsIp.get(2), hostsIp.get(3), 1);
        momentsView2.setBounds(momentStartX + momentsViewWidth + momentsViewsDistance + momentsViewWidth + momentsViewsDistance, momentStartY, momentsViewWidth, momentsViewHeight);
        add(momentsView2);
        momentsViews.add(momentsView2);

        MomentsView momentsView3 = new MomentsView(3, hostsIp.get(4), hostsIp.get(5), 1);
        momentsView3.setBounds(momentStartX + momentsViewWidth + momentsViewsDistance, momentsViewHeight + momentsViewsDistance + momentStartY, momentsViewWidth, momentsViewHeight);
        add(momentsView3);
        momentsViews.add(momentsView3);
        MomentsView momentsView4 = new MomentsView(4, hostsIp.get(6), hostsIp.get(7), 1);
        momentsView4.setBounds(momentStartX + momentsViewWidth + momentsViewsDistance + momentsViewWidth + momentsViewsDistance, momentsViewHeight + momentsViewsDistance + momentStartY, momentsViewWidth, momentsViewHeight);
        add(momentsView4);
        momentsViews.add(momentsView4);


//        new Thread(() -> {
//            RandomUtil randomUtil = RandomUtil.getInstance();
//            while (true) {
//
//                Moment moment = randomUtil.buildRandomMoment();
//                momentsViewRouter.addMoment(moment);
//            }
//        }).start();


    }

    public void setDiyBackGround() {
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("/Users/dmrfcoder/IdeaProjects/SimulationRouter/res/background.png");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }


    public static void enableOSXFullscreen(Window window) {
        try {
            Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
            Class params[] = new Class[]{Window.class, Boolean.TYPE};
            Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void requestOSXFullscreen(Window window) {
        try {
            Class appClass = Class.forName("com.apple.eawt.Application");
            Class params[] = new Class[]{};

            Method getApplication = appClass.getMethod("getApplication", params);
            Object application = getApplication.invoke(appClass);
            Method requestToggleFulLScreen = application.getClass().getMethod("requestToggleFullScreen", Window.class);

            requestToggleFulLScreen.invoke(application, window);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void changeToRunning() {
        iMainPresenter.startAll();
    }

    @Override
    public void changeToStop() {

        iMainPresenter.stopAll();
    }


    @Override
    public void initRouterSuccess(ArrayList<Integer> ports) {
        initMemoryCircleViews(ports);

    }

    @Override
    public void initHostsSuccess(ArrayList<String> hostsIp) {
        initMomentsViews(hostsIp);
    }

    @Override
    public void addToRouter(int index, String content, int type) {
        // System.out.println("addToRouter:" + content);
        if (index == 0) {
            momentsViewRouter.addMoment(new Moment(content, type));
        }
    }

    @Override
    public void addToHost(int index, String content, int type) {

        //System.out.println("addToHost:" + index);
        if (index >= 0 && index < momentsViews.size()) {
            momentsViews.get(index).addMoment(new Moment(content, type));
        }
    }

    @Override
    public void updatePercentage(int index, float percentage, int messageCount) {
        //System.out.println("updatePercentage:" + percentage);
        if (index >= 0 && index < memoryCircleViews.size()) {
            memoryCircleViews.get(index).updatePercentage(percentage, messageCount);
        }
    }
}

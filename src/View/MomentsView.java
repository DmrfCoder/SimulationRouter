package View;

import Bean.Moment;
import Configure.ViewConfigure;
import Util.RandomUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public class MomentsView extends JComponent implements IMomentsView {

    private int momentsMaxCount = 17;

    private LinkedList<Moment> moments;
    private LinkedList<JLabel> jLabels;

    private int windowHeight;
    private int windowWidth;

    private int hostId;
    private String hostIp;
    private String hostPort=null;
    private int type;
    private int textHeight = 20;

    public MomentsView(int hostId, String hostIp, int type, int momentsMaxCount) {
        this.hostId = hostId;
        this.hostIp = hostIp;
        this.momentsMaxCount = momentsMaxCount;
        this.type = type;

        windowHeight = momentsMaxCount * (350 / 16);
        windowWidth = 300;
        moments = new LinkedList<>();
        jLabels = new LinkedList<>();
        initView();

    }

    public MomentsView(int hostId, String hostIp,String hostPort, int type) {
        momentsMaxCount = 16;
        this.hostId = hostId;
        this.hostIp = hostIp;
        this.momentsMaxCount = momentsMaxCount;
        this.type = type;
        this.hostPort=hostPort;

        windowHeight = 350;
        windowWidth = 300;
        moments = new LinkedList<>();
        jLabels = new LinkedList<>();
        initView();

    }


    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    @Override
    public void initView() {


    }

    @Override
    public void addMoment(Moment moment) {
        if (moments.size() < momentsMaxCount) {
            moments.add(moment);
        } else {
            moments.remove(0);
            moments.add(moment);

        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(ViewConfigure.defaultTextColor);
        g.drawRect(0, 0, windowWidth, windowHeight - 20);


        for (int index = 0; index < moments.size(); index++) {
            int itemY = textHeight * (index + 1);

            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = df.format(new Date());
                if (moments.get(index).getMomentType() == 0) {
                    g.setColor(ViewConfigure.defaultTextColor);
                } else {
                    g.setColor(Color.red);
                }


                g.drawString(time.substring(11) + ":" + moments.get(index).getMomentContent(), 0, itemY);
            } catch (Exception e) {
                int a = 0;
            }

        }
        g.setColor(ViewConfigure.defaultTextColor);

        if (type == 0) {
            g.drawString("路由器：" + hostId, (windowWidth - 100) / 2, windowHeight - 5);
        } else {
            g.drawString("主机：" + hostId + " ip:" + hostIp+" port:"+hostPort, (windowWidth - 250) / 2, windowHeight - 5);
        }


    }
}

package View;

import Bean.Moment;
import Configure.ViewConfigure;
import Util.RandomUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public class MomentsView extends JComponent implements IMomentsView {

    private int momentsMaxCount;

    private volatile Vector<Moment> moments;

    private int windowHeight;
    private int windowWidth;

    private int hostId;
    private String hostIp;
    private String hostPort = null;
    private int type;
    private int textHeight = 20;

    private volatile int curCount;

    public MomentsView(int hostId, String hostIp, int type, int momentsMaxCount,int windowWidth) {
        this.hostId = hostId;
        this.hostIp = hostIp;
        this.momentsMaxCount = momentsMaxCount;
        this.type = type;

        windowHeight = (momentsMaxCount + 2) * textHeight;
        this.windowWidth = windowWidth;
        moments = new Vector<>();
        curCount = 0;
        initView();

    }

    public MomentsView(int hostId, String hostIp, String hostPort, int type) {

        this.hostId = hostId;
        this.hostIp = hostIp;
        this.momentsMaxCount = 20;
        this.type = type;
        this.hostPort = hostPort;

        windowHeight = (momentsMaxCount + 1) * textHeight;
        windowWidth = 300;
        moments = new Vector<>();
        curCount = 0;
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
        if (curCount < momentsMaxCount) {
            moments.add(moment);
            curCount++;
        } else {
            moments.remove(0);
            curCount--;

            moments.add(moment);
            curCount++;

        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(ViewConfigure.defaultTextColor);
        g.drawRect(0, 0, windowWidth, windowHeight - 20);


        for (int index = 0; index < curCount; index++) {
            int itemY = textHeight * index;
            if (itemY >= windowHeight - 20) {
                break;
            }

            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = df.format(new Date());
                if (moments.get(index).getMomentType() == 0) {
                    g.setColor(ViewConfigure.defaultTextColor);
                } else if (moments.get(index).getMomentType() == 1) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.red);
                }


                g.drawString(time.substring(11) + ":" + moments.get(index).getMomentContent(), 0, itemY);
            } catch (Exception e) {
                System.out.println("Exception-MomentView-paint:"+e.getLocalizedMessage());
            }

        }
        g.setColor(ViewConfigure.defaultTextColor);

        if (type == 0) {
            g.drawString("路由器：" + hostId, (windowWidth - 100) / 2, windowHeight - 5);
        } else {
            g.drawString("主机：" + hostId + " ip:" + hostIp + " port:" + hostPort, (windowWidth - 250) / 2, windowHeight - 5);
        }


    }
}

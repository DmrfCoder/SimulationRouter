package View;

import javax.swing.*;
import java.awt.*;

/**
 * @author dmrfcoder
 * @date 2019-04-16
 */
public class MemoryCircleView extends JComponent {

    private Color circleBackGroundColor;
    private int smallestD;
    private int biggestD;
    private int curD;

    private int windowWidth;
    private int windowHeight;

    private int redInt = -56636;
    private int greenInt = -16711936;
    private int colorLength = redInt - greenInt;
    private int singleCorlorLength = colorLength / 100;
    private double singleDLength;
    private int port;
    private int interfaceId;

    private double curValue;


    public MemoryCircleView(int port, int interfaceId) {
        this.port = port;
        this.interfaceId = interfaceId;
        circleBackGroundColor = Color.MAGENTA;


        windowHeight = 250;
        windowWidth = 200;


        smallestD = windowWidth / 4;
        biggestD = windowWidth;

        curD = smallestD;


        singleDLength = (biggestD - smallestD) / 100.0;
    }


    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, windowWidth, windowHeight);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(circleBackGroundColor);
        g.fillOval((windowWidth - curD) / 2, (windowHeight - curD) / 2, curD, curD);
        g.setColor(new Color(113,213,250));

        g.drawString("接口：" + interfaceId + "，端口：" + port, (windowWidth - 130) / 2, windowHeight - 5);

        g.setColor(Color.BLACK);
        g.drawString(curValue + "%", (windowWidth - 30) / 2, 5+windowHeight  / 2);

        g.setColor(new Color(113,213,250));
        g.drawRect(0, 0, windowWidth, windowHeight);

    }

    public void updatePercentage(double value) {
        //value 在0到100之间,0代表greenInt，100代表redInt

        if (value <= 100 && value > 0) {
            curValue = value;
            int curR = (int) (2.55 * value);
            int curG = (int) (255 - 2.55 * value);

            circleBackGroundColor = new Color(curR, curG, 0);

            curD = (int) (smallestD + singleDLength * value);
        }
        repaint();

    }


}

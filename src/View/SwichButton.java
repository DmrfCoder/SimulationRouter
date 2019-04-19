package View;

import Configure.ViewConfigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public class SwichButton extends JLabel {

    //0代表开始/绿色(停止中)，1代表结束/红色（运行中）
    private int curState;

    private int D;

    interface StateChangeListener {
        void changeToRunning();

        void changeToStop();
    }

    private StateChangeListener stateChangeListener;

    public void setStateChangeListener(StateChangeListener stateChangeListener) {
        this.stateChangeListener = stateChangeListener;
    }

    public SwichButton(int D) {
        curState = 0;
        this.D = D;

        initListener();

    }


    private void initListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (curState == 0) {
                    curState = 1;
                    stateChangeListener.changeToRunning();
                } else {
                    curState = 0;
                    stateChangeListener.changeToStop();
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (curState == 0) {
            g.setColor(ViewConfigure.defaultTextColor);
        } else {
            g.setColor(Color.red);
        }
        g.fillOval((getWidth() - D) / 2, (getHeight() - D) / 2, D, D);


        String text = "";
        g.setColor(Color.BLACK);
        if (curState == 0) {
            text = "开始";

        } else {
            text = "结束";
        }

        g.drawString(text, (getWidth() - 25) / 2, 5 + getHeight() / 2);

    }


}

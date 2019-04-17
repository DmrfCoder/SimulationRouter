package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public class SwichButton extends JButton {

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
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click");
                if (curState == 0) {
                    curState = 1;
                    stateChangeListener.changeToRunning();
                } else {
                    curState = 0;
                    stateChangeListener.changeToStop();
                }
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (curState == 0) {
            g.setColor(Color.green);
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

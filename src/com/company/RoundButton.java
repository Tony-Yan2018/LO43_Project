package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RoundButton extends JButton {
    int i ;
    boolean clicked = false;
    int count = 0;
    public RoundButton(String s,int n) {
        super(s);
        i=n;
        setFont(new Font("Times New Roman",Font.CENTER_BASELINE,11));
        //allows to draw our own background instead of auto-filling the bounding box
        setContentAreaFilled(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.act==0) {
                    clicked = true;
                    updateInfo(++count);

                }  }
        });
    }
    protected void paintComponent(Graphics g) {
        if(clicked&&count==1) {
            g.setColor(Color.magenta);
            this.setForeground(Color.white);
        }
        else if(clicked&&count==2){
            g.setColor(Color.magenta);
        }
        else {
            g.setColor(Color.white);
            setForeground(Color.black);
        }
        g.fillOval(1, 1, 40, 40);
        super.paintComponent(g);//number string does not display without this line
    }
    // paint the bounding circle of the button
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.8f));
        g2d.drawOval(1, 1, 40, 40);
        if(clicked&&count==2){
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2.8f));
            g2d.drawOval(11, 11, 20, 20);
        }
    }
    void updateInfo(int count){
        switch (count){
            case (1):  MyListOfVertex.va[i].village=true;
            break;
            case (2): MyListOfVertex.va[i].city=true;
            break;
            case (3): MyListOfVertex.va[i].city=false;
                        MyListOfVertex.va[i].village=false;
                        break;
    }
    }
}


package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.*;

public class RoundButton extends JButton {

    public RoundButton(String s) {
        super(s);
        setFont(new Font("Times New Roman",Font.CENTER_BASELINE,11));
         //allows to draw our own background instead of auto-filling the bounding box
       setContentAreaFilled(false);
       addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                  }
       });
    }
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
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
    }

//    // 侦测点击事件
//    Shape shape;
//
//    public boolean contains(int x, int y) {
//        // 如果按钮改变大小，产生一个新的形状对象。
//        if (shape == null || !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
//        }
//        return shape.contains(x, y);
//
//    }
}


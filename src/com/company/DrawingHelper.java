package com.company;


import java.awt.*;
import java.awt.geom.Ellipse2D;

/*
This class contains most of the drawing methods used in the project
* */
public class DrawingHelper {
    //helping class cannot be instanced
    private void DrawingHelper(){};
    //shared drawing methods

    public static void setHexColor(Graphics2D g2h,Shape s,Color c){
        g2h.setColor(c);
        g2h.fill(s);
    }
    public static void setHexStroke(Graphics2D g2h,Shape s,Color c){
        g2h.setColor(c);
        g2h.setStroke(new BasicStroke(4f));//Sets the Stroke for the Graphics2D context.
        g2h.draw(s);//Strokes the outline of a Shape using the settings of the current Graphics2D context
    }
    public static void drawCircle(Graphics2D g2h,double r, double xCoor, double yCoor,Color c){
        Ellipse2D circle = new Ellipse2D.Double(2*r,2*r,xCoor-r,yCoor-r);
        circle.setFrameFromCenter(xCoor,yCoor,xCoor+r,yCoor+r);
        g2h.setColor(c);
        g2h.draw(circle);
    }

}

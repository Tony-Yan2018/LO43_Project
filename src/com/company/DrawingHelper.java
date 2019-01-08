package com.company;

import java.awt.*;

/*
This class contains most of the drawing methods used in the project
* */
 class DrawingHelper {
    //helping class should not be instanced
    private  DrawingHelper(){}
    //drawing methods
    static void setHexColor(Graphics2D g2h,Shape s,Color c){
        g2h.setColor(c);
        g2h.fill(s);
    }
    static void setHexStroke(Graphics2D g2h,Shape s){
        g2h.setColor(Color.BLACK);
        g2h.setStroke(new BasicStroke(4f));//Sets the Stroke for the Graphics2D context.
        g2h.draw(s);//Strokes the outline of a Shape using the settings of the current Graphics2D context
    }
    static void setBiffStroke(Graphics2D g2d,int xCoord,int yCoord){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2.8f));
        g2d.drawOval(xCoord-8,yCoord-5, 25, 25);
    }
    static void drawRoad(Graphics2D g2d,int left,int right,int pl){
        g2d.setStroke(new BasicStroke(8f));
        g2d.setPaint(Controller.players[pl].color);
        g2d.drawLine((int)(MyListOfVertex.va[left].xCoord),(int)(MyListOfVertex.va[left].yCoord),(int)(MyListOfVertex.va[right].xCoord),(int)(MyListOfVertex.va[right].yCoord));
    }
    static void drawPlayerColorIndications(Graphics2D g2d,int x,int y,Color color){
        g2d.setColor(color);
        g2d.fillRect(x,y,20,20);
    }
}

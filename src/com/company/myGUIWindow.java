package com.company;
import javax.swing.*;
import java.awt.*;
import  java.awt.geom.Path2D;

public class myGUIWindow extends JFrame{
    private int MyWidth;
    private int MyHeight;

    public int getMyWidth() {
        return MyWidth;
    }
    public int getMyHeight(){
        return MyHeight;
    }

    public myGUIWindow(String title, int canvasWidth, int canvasHeight){
        super(title);

        MyWidth=canvasWidth;
        MyHeight=canvasHeight;

        MyPanel canvas=new MyPanel();
        setContentPane(canvas);
        pack();
        //canvas.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);
    }
    private class MyPanel extends JPanel{
        public MyPanel() {
            for(int i=0;i<19;i++)
                add(Controller.getHEX()[i].diceRes);//add JLabels of Hexagons
            for(Vertex v:MyListOfVertex.va){
                add(Controller.RB[v.num]);//add RoundButtons
            }
            for(int i=0;i<16;i++) {
                add(Controller.JB[i]);
            }
            for(int i=0;i<4;i++)
                add(Controller.Jscrolls[i]);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            //drawing procedure of each hexagon
            for(int i=0;i<19;i++) {
                Path2D polyline = new Path2D.Double();
                //moving to the specified coordinates
                polyline.moveTo(Controller.getHEX()[i].xCoordinates[0], Controller.getHEX()[i].yCoordinates[0]);
                //the loop to traverse six vertex
                for (int j = 0; j < Controller.getHEX()[i].xCoordinates.length; j++) {
                    //drawing a straight line from the current coordinates to the new specified coordinates
                    polyline.lineTo(Controller.getHEX()[i].xCoordinates[j], Controller.getHEX()[i].yCoordinates[j]);
                    g2.draw(polyline);
                }
                polyline.closePath();
                g2.draw(polyline);
                Shape hex = polyline;

                DrawingHelper.setHexColor(g2, hex, Controller.getHEX()[i].HG.ColorRes);
                DrawingHelper.setHexStroke(g2, hex, Color.black);

                //set number(JLabel) of each hex in its proper position
                Controller.getHEX()[i].diceRes.setBounds((int)Controller.getHEX()[i].xCenter,(int)Controller.getHEX()[i].yCenter,100,15);

        }
//          /*add RoundButtons to the screen*/
            for(Vertex v:MyListOfVertex.va) {
                Controller.RB[v.num].setBounds((int) v.xCoord - 20, (int) v.yCoord - 20, 45, 45);
            }
            for(int i=0;i<16;i++)
                Controller.JB[i].setBounds((Controller.ListButtons.Pb[i].xcoord), Controller.ListButtons.Pb[i].ycoord, Controller.ListButtons.Pb[i].xsize, Controller.ListButtons.Pb[i].ysize);
            for(int i=0;i<4;i++)
                Controller.Jscrolls[i].setBounds(Controller.ListT.coord[i][0], Controller.ListT.coord[i][1], Controller.ListT.coord[i][2], Controller.ListT.coord[i][3]);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(MyWidth,MyHeight);
        }/*determine the size of the canvas in its proper class
         */
    }
}
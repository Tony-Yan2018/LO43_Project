package com.company;
import javax.swing.*;
import java.awt.*;
import  java.awt.geom.Path2D;

public class myGUIWindow extends JFrame{
    private int MyWidth;
    private int MyHeight;
    public static MyPanel canvas;

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

        canvas=new MyPanel();

        setContentPane(canvas);
        pack();
        //canvas.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);
    }
    public class MyPanel extends JPanel{
        public MyPanel() {
            for(int i=0;i<19;i++) {
                add(Controller.getHEX()[i].diceRes);//add JLabels of Hexagons
            }
            for(Vertex v:MyListOfVertex.va){
                add(Controller.RB[v.num]);//add RoundButtons
            }
            for(int i=0;i<16;i++) {
                add(Controller.playerButtons[i]);
            }
            for(int i=0;i<4;i++) {
                add(Controller.Jscrolls[i]);
            }
            add(Controller.dice.l1);
            add(Controller.dice.l2);
            add(Controller.dice.l3);
            add(Controller.dice.l4);
            add(Controller.dice.l5);
            add(Controller.dice.b);
            add(Controller.turn);
            add(Controller.turnB);
            add(Controller.mapChanger);
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
                if(Controller.getHEX()[i].HG.Biff){
                    DrawingHelper.setBiffStroke(g2,(int)Controller.getHEX()[i].xCenter,(int)Controller.getHEX()[i].yCenter);
                }
                //set number(JLabel) of each hex in its proper position
                Controller.getHEX()[i].diceRes.setBounds((int)Controller.getHEX()[i].xCenter,(int)Controller.getHEX()[i].yCenter,100,15);
        }
            for(int i=0;i<4;i++){//draw the roads constructed by 4 players
                Road current = Controller.ALR.RArray[i];
                while(current.next!=null){
                    current=current.next;
                    DrawingHelper.drawRoad(g2,current.leftPoint,current.rightPoint,i);
                }

            }
//          /*add RoundButtons to the screen*/
            for(Vertex v:MyListOfVertex.va) {
                Controller.RB[v.num].setBounds((int) v.xCoord - 20, (int) v.yCoord - 20, 45, 45);
            }
            for(int i=0;i<16;i++)
                Controller.playerButtons[i].setBounds(Controller.playerButtons[i].xcoord,Controller.playerButtons[i].ycoord,Controller.playerButtons[i].xsize,Controller.playerButtons[i].ysize);
            for(int i=0;i<4;i++)
                Controller.Jscrolls[i].setBounds(Controller.ListT.coord[i][0], Controller.ListT.coord[i][1], Controller.ListT.coord[i][2], Controller.ListT.coord[i][3]);
            Toolkit t = Toolkit.getDefaultToolkit();
            Dimension Dt = t.getScreenSize();
            Controller.dice.l1.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.35),100,30);
            Controller.dice.l2.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.4),50,30);
            Controller.dice.l3.setBounds((int)(Dt.getWidth()*0.06),(int)(Dt.getHeight()*0.4),50,30);
            Controller.dice.l4.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.45),100,30);
            Controller.dice.l5.setBounds((int)(Dt.getWidth()*0.06),(int)(Dt.getHeight()*0.45),50,30);
            Controller.dice.b.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.5),150,30);

            Controller.turn.setBounds((int)(Dt.getWidth()*0.85),(int)(Dt.getHeight()*0.45),300,30);
            Controller.turnB.setBounds((int)(Dt.getWidth()*0.88),(int)(Dt.getHeight()*0.50),100,30);

            Controller.mapChanger.setBounds((int)(Dt.getWidth()*0.45),10,150,30);

        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(MyWidth,MyHeight);
        }/*determine the size of the canvas in its proper class
         */
    }
}
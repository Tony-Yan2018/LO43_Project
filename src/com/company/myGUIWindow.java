package com.company;
import javax.swing.*;
import java.awt.*;
import  java.awt.geom.Path2D;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);
    }

    private class MyPanel extends JPanel{
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
                this.add(Controller.getHEX()[i].diceRes);//and add it to the screen
        }
//          /*add Buttons*/
            for(Vertex v:MyListOfVertex.va){
                /*To resolve the problem of low response,I try to generate JButton in separate threads.*/
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        v.RB.setBounds((int)v.xCoord-20,(int)v.yCoord-20,45,45);
                        add(v.RB);
                    }
                });

//                t1.start();
//                t1.stop();
//                try{
//                t1.join(1000);}
//                catch(InterruptedException ie){
//                    ie.printStackTrace();
//                }
//                Thread t2 = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Font f = new Font("Times New Roman",Font.PLAIN,8);
//                        v.RB.JL.setFont(f);
//                        add(v.RB.JL);
//                    }
//                },"T2");
//                ExecutorService executor = Executors.newSingleThreadExecutor();
//                executor.submit(t1);
//                executor.submit(t2);
//                executor.shutdown();







            }
//            JComboBox<Vertex> combo = new JComboBox<Vertex>();
//            combo.setBounds(900,50,100,50);
//            add(combo);
            }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(MyWidth,MyHeight);
        }/*determine the size of the canvas in its proper class
         */
    }

}
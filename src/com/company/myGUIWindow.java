package com.company;
import userInterface.Dice;
import userInterface.ListOfTable;
import userInterface.UButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.awt.geom.Path2D;
import java.util.Random;

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
         RoundButton[] RB = new RoundButton[54];
         UButton[] playerButtons=new UButton[16];

        public  ListOfTable ListT;
        public  JScrollPane[] Jscrolls=new JScrollPane[4];
        public  Dice dice = new Dice();
        public  JLabel turn = new JLabel("The current player is: "+"Player"+String.valueOf(Controller.flag+1));
        public  JButton turnB = new JButton("End Turn");
        public  JButton mapChanger = new JButton("Change the map");
        public  JLabel mapChangeReminder = new JLabel("(Available only if a wormhole is triggered)");
        public MyPanel() {
            for(int i=0;i<16;i++) {
                playerButtons[i]=new UButton(i);
            }
            for(int i=0;i<54;i++){
                RB[i]= new RoundButton(String.valueOf(i),i);
            }
            ListT=new ListOfTable(Controller.players);
            for(int i=0;i<4;i++){//set the 4 tables with 4 JScrollPanes
                Jscrolls[i]=new JScrollPane(ListT.LT[i]);
            }
            turnB.addActionListener(new ActionListener() {//click to end turn
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Controller.flag<3)
                        Controller.flag++;
                    else{
                        Controller.flag=0;
                    }
                    if(Controller.initialTurnCount>=4){
                        if(Controller.ALR.roadKingDeterminer()!=-1){//if there is a road king
                            if(Controller.lastRoadKing!=-1){//if last road king exists
                                Controller.players[Controller.lastRoadKing].roadKing=false;
                                Controller.players[Controller.lastRoadKing].score-=2;
                            }
                            Controller.lastRoadKing=Controller.ALR.roadKingDeterminer();//set current road king as the last
                            Controller.players[Controller.ALR.roadKingDeterminer()].roadKing=true;
                            Controller.players[Controller.ALR.roadKingDeterminer()].score+=2;
                        }
                    }
                    turn.setText("The current player is: "+"Player"+String.valueOf(Controller.flag+1));
                }
            });
            mapChanger.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Controller.wormholeClicked){
                        Controller.wormholeClicked=false;
                        Random ran = new Random();
                        Controller.mapID = ran.nextInt(4)+1;
                        Controller.map=Maps.mapDeterminer(Controller.mapID);
                        for(int i=0;i<19;i++){
                            myGUIWindow.canvas.remove(Controller.getHEX()[i].diceRes);
                        }
                        for(int i=0;i<19;i++){
                            Controller.getHEX()[i]=new Hexagone(Controller.xCoord,Controller.yCoord,Controller.side,i);
                        }
                        for(int i=0;i<19;i++){
                            myGUIWindow.canvas.add(Controller.getHEX()[i].diceRes);
                        }
                        //                System.out.println(HEX[0].diceRes.getText());
                        myGUIWindow.canvas.validate();
                        myGUIWindow.canvas.updateUI();
                        myGUIWindow.canvas.repaint();


                        JOptionPane.showMessageDialog(null,"Welcome to "+Maps.year);
                    }
                }
            });
            for(int i=0;i<19;i++) {
                add(Controller.getHEX()[i].diceRes);//add JLabels of Hexagons
            }
            for(Vertex v:MyListOfVertex.va){
                add(RB[v.num]);//add RoundButtons
            }
            for(int i=0;i<16;i++) {
                add(playerButtons[i]);
            }
            for(int i=0;i<4;i++) {
                add(Jscrolls[i]);
            }
            add(dice.l1);
            add(dice.l2);
            add(dice.l3);
            add(dice.l4);
            add(dice.l5);
            add(dice.b);
            add(turn);
            add(turnB);
            add(mapChanger);
            add(mapChangeReminder);
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
                RB[v.num].setBounds((int) v.xCoord - 20, (int) v.yCoord - 20, 45, 45);
            }
            for(int i=0;i<16;i++)
                playerButtons[i].setBounds(playerButtons[i].xcoord,playerButtons[i].ycoord,playerButtons[i].xsize,playerButtons[i].ysize);
            for(int i=0;i<4;i++)
                Jscrolls[i].setBounds(ListT.coord[i][0], ListT.coord[i][1], ListT.coord[i][2], ListT.coord[i][3]);
            Toolkit t = Toolkit.getDefaultToolkit();
            Dimension Dt = t.getScreenSize();
            dice.l1.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.35),100,30);
            dice.l2.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.4),50,30);
            dice.l3.setBounds((int)(Dt.getWidth()*0.06),(int)(Dt.getHeight()*0.4),50,30);
            dice.l4.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.45),100,30);
            dice.l5.setBounds((int)(Dt.getWidth()*0.06),(int)(Dt.getHeight()*0.45),50,30);
            dice.b.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.5),150,30);

            turn.setBounds((int)(Dt.getWidth()*0.85),(int)(Dt.getHeight()*0.45),300,30);
            turnB.setBounds((int)(Dt.getWidth()*0.88),(int)(Dt.getHeight()*0.50),100,30);

            mapChanger.setBounds((int)(Dt.getWidth()*0.45),10,150,30);
            mapChangeReminder.setBounds((int)(Dt.getWidth()*0.45)+150,10,300,30);

        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(MyWidth,MyHeight);
        }/*determine the size of the canvas in its proper class
         */
    }
}
package com.company;
import userInterface.Dice;
import userInterface.ListOfTable;
import userInterface.MyTable;
import userInterface.UButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.awt.geom.Path2D;
import java.util.ArrayList;
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

          ListOfTable ListT;
          JScrollPane[] Jscrolls=new JScrollPane[4];
          Dice dice = new Dice();
          JLabel turn = new JLabel("The current player is: "+"Player"+String.valueOf(Controller.flag+1));
          JButton turnB = new JButton("End Turn");
          JButton mapChanger = new JButton("Change the map");
          JLabel mapChangeReminder = new JLabel("(Available only if a wormhole is triggered)");
          JButton refresh = new JButton("Refresh");
          JLabel resColor1 = new JLabel("Mineral");
          JLabel resColor2 = new JLabel("Grain");
          JLabel resColor3 = new JLabel("Textile");
          JLabel resColor4 = new JLabel("Building Materials");
         MyPanel() {
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
                    updateJTables();
                    if(Controller.flag<3)
                        Controller.flag++;
                    else{
                        Controller.flag=0;
                    }
                    if(Controller.initialTurnCount>=4){
                    	Dice.DiceClick=true;
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
                    for(int i=0;i>3;i++) {
                    	if(Controller.players[i].score>=20) {
                    		JOptionPane.showMessageDialog(null, "Player"+(i+1)+"is the winner!");
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
                        while(Controller.mapID==4){
                            Controller.mapID = ran.nextInt(4)+1;
                        }//to exclude 1985
                        dataTransfer();
                        myGUIWindow.canvas.validate();
                        myGUIWindow.canvas.updateUI();
                        myGUIWindow.canvas.repaint(); 


                        JOptionPane.showMessageDialog(null,"Welcome to "+Maps.year);
                    }
                    updateJTables();
                }
            });
            refresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateJTables();
                    canvas.repaint();
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
            add(refresh);
            add(resColor1);
            add(resColor2);
            add(resColor3);
            add(resColor4);
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
            refresh.setBounds((int)(Dt.getWidth()*0.02),(int)(Dt.getHeight()*0.25),150,30);

            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.16),5,Controller.players[0].color);
            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.82),5,Controller.players[2].color);
            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.16),(int)(Dt.getHeight()*0.82),Controller.players[1].color);
            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.82),(int)(Dt.getHeight()*0.82),Controller.players[3].color);

            resColor1.setBounds((int)(Dt.getWidth()*0.9),(int)(Dt.getHeight()*0.23),50,30);
            resColor2.setBounds((int)(Dt.getWidth()*0.9),(int)(Dt.getHeight()*0.23+30),50,30);
            resColor3.setBounds((int)(Dt.getWidth()*0.9),(int)(Dt.getHeight()*0.23+60),50,30);
            resColor4.setBounds((int)(Dt.getWidth()*0.9),(int)(Dt.getHeight()*0.23+90),120,30);

            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.9)-25,(int)(Dt.getHeight()*0.24),Color.pink);
            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.9)-25,(int)(Dt.getHeight()*0.24+30),Color.yellow);
            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.9)-25,(int)(Dt.getHeight()*0.24+60),Color.cyan);
            DrawingHelper.drawPlayerColorIndications(g2,(int)(Dt.getWidth()*0.9)-25,(int)(Dt.getHeight()*0.24+90),Color.orange);
        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(MyWidth,MyHeight);
        }/*determine the size of the canvas in its proper class
         */
    }
    public static void updateJTables(){
        switch (Controller.mapID) {
            case (4)://in 1985
                for (int i = 0; i < 4; i++) {
                    Object[][] rowData;
                    rowData = new Object[][]{
                            {"Silver", Controller.players[i].resource[0]},
                            {"Rice", Controller.players[i].resource[1]},
                            {"Wool", Controller.players[i].resource[2]},
                            {"Cement", Controller.players[i].resource[3]},
                            {"Score", Controller.players[i].score},
                            {"Harvest Card", Controller.players[i].harvestCard},
                            {"Road Card", Controller.players[i].roadCard},
                            {"Score Card", Controller.players[i].currentScoreCard()}
                    };
                    canvas.ListT.LT[i] = new MyTable(rowData, new Object[]{"Player" + (i + 1), "1985"});
                }
                for (int i = 0; i < 4; i++) {//set the 4 tables with 4 JScrollPanes
                    canvas.Jscrolls[i] = new JScrollPane(canvas.ListT.LT[i]);
                    canvas.add(canvas.Jscrolls[i]);
                }
                break;
            case (1)://1885
                for (int i = 0; i < 4; i++) {
                    Object[][] rowData;
                    rowData = new Object[][]{
                            {"Iron", Controller.players[i].resource[0]},
                            {"Millet", Controller.players[i].resource[1]},
                            {"Fibre", Controller.players[i].resource[2]},
                            {"Wood", Controller.players[i].resource[3]},
                            {"Score", Controller.players[i].score},
                            {"Harvest Card", Controller.players[i].harvestCard},
                            {"Road Card", Controller.players[i].roadCard},
                            {"Score Card", Controller.players[i].currentScoreCard()}
                    };
                    canvas.ListT.LT[i] = new MyTable(rowData, new Object[]{"Player" + (i + 1), "1885"});
                }
                for (int i = 0; i < 4; i++) {//set the 4 tables with 4 JScrollPanes
                    canvas.Jscrolls[i] = new JScrollPane(canvas.ListT.LT[i]);
                    canvas.add(canvas.Jscrolls[i]);
                }
                break;
            case(2):
                for (int i = 0; i < 4; i++) {
                    Object[][] rowData;
                    rowData = new Object[][]{
                            {"Cooper", Controller.players[i].resource[0]},
                            {"Wheat", Controller.players[i].resource[1]},
                            {"Cotton", Controller.players[i].resource[2]},
                            {"Steel", Controller.players[i].resource[3]},
                            {"Score", Controller.players[i].score},
                            {"Harvest Card", Controller.players[i].harvestCard},
                            {"Road Card", Controller.players[i].roadCard},
                            {"Score Card", Controller.players[i].currentScoreCard()}
                    };
                    canvas.ListT.LT[i] = new MyTable(rowData, new Object[]{"Player" + (i + 1), "1955"});
                }
                for (int i = 0; i < 4; i++) {//set the 4 tables with 4 JScrollPanes
                    canvas.Jscrolls[i] = new JScrollPane(canvas.ListT.LT[i]);
                    canvas.add(canvas.Jscrolls[i]);
                }
            break;
            case (3):
                for (int i = 0; i < 4; i++) {
                    Object[][] rowData;
                    rowData = new Object[][]{
                            {"Diamond", Controller.players[i].resource[0]},
                            {"Potato", Controller.players[i].resource[1]},
                            {"Silk", Controller.players[i].resource[2]},
                            {"Concrete", Controller.players[i].resource[3]},
                            {"Score", Controller.players[i].score},
                            {"Harvest Card", Controller.players[i].harvestCard},
                            {"Road Card", Controller.players[i].roadCard},
                            {"Score Card", Controller.players[i].currentScoreCard()}
                    };
                    canvas.ListT.LT[i] = new MyTable(rowData, new Object[]{"Player" + (i + 1), "2015"});
                }
                for (int i = 0; i < 4; i++) {//set the 4 tables with 4 JScrollPanes
                    canvas.Jscrolls[i] = new JScrollPane(canvas.ListT.LT[i]);
                    canvas.add(canvas.Jscrolls[i]);
                }


        }
    }
    public static void dataTransfer(){
        Controller.map=Maps.mapDeterminer(Controller.mapID);
        for(int i=0;i<19;i++){
            myGUIWindow.canvas.remove(Controller.getHEX()[i].diceRes);
        }
        Hexagone [] hex = new Hexagone[19];
        for(int i=0;i<19;i++){
            hex[i] =new Hexagone(Controller.xCoord,Controller.yCoord,Controller.side,i);
            hex[i].HG.IndicesPlayer=Controller.getHEX()[i].HG.IndicesPlayer;
            hex[i].HG.Biff= Controller.getHEX()[i].HG.Biff;
        }
        for(int i=0;i<19;i++){
            Controller.getHEX()[i]=new Hexagone(Controller.xCoord,Controller.yCoord,Controller.side,i);
            Controller.getHEX()[i].HG.Biff=hex[i].HG.Biff;
            Controller.getHEX()[i].HG.IndicesPlayer=hex[i].HG.IndicesPlayer;
        }
        for(int i=0;i<19;i++){
            myGUIWindow.canvas.add(Controller.getHEX()[i].diceRes);
        }
    }
}

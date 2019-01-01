package com.company;

import inGame.Player;
import userInterface.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Controller {
    //data field
    private static Hexagone[] HEX;
    private static int[] map=new int[19];
    static HashSet<Integer> HSet = new HashSet<>(3);
    AdjacencyList AL ;
    public static int act= -1;//0 to build village, 1 to build city, 2 to build road
    protected static int initialTurnCount=0;
    public static int flag = 0; // to identify the current player ranging from 0 to 4

    //view field
    private static myGUIWindow Frame;
    static RoundButton[] RB = new RoundButton[54];
    static UButton[] playerButtons=new UButton[16];
    public static Player[] players = new Player[4];
    public static ListOfTable ListT;
    public static JScrollPane[] Jscrolls=new JScrollPane[4];
    public static Dice dice = new Dice();
    public static JLabel turn = new JLabel("The current player is: "+"Player"+String.valueOf(Controller.flag+1));
    public static JButton turnB = new JButton("End Turn");
    //getters
    public static Hexagone[] getHEX() {
        return HEX;
    }
    public static int[] getMap(){
        return map;
    }
    //constructor
    public Controller(double xC,double yC,double s,String t,int Width,int Height,int m){
        //initialize data
        for(int i=0;i<4;i++)
            players[i]=new Player(i);//initialize the 4 players
        HEX=new Hexagone[19];
        map=Maps.mapDeterminer(m);
        for(int i=0;i<19;i++){
            HEX[i]=new Hexagone(xC,yC,s,i);
        }
        HSetAdder(HSet);
        wormholeSetter(HEX,HSet);
        //scan each vertex of each hexagon to add all vertex to the array without redundancy
        for(int i=0;i<19;i++){
            for(int j=0;j<6;j++){
                MyListOfVertex.getInstance().traverseToAdd(new Vertex(HEX[i].xCoordinates[j],HEX[i].yCoordinates[j],i),i);
            }
        }
        //set number to identify the array index of the Vertex
        for(int i=0;i<MyListOfVertex.va.length;i++){
            MyListOfVertex.va[i].num=i;
            RB[i] = new RoundButton(String.valueOf(i),i);
        }
        //instance a new Adjacency List with the parameter of the vertex array
        AL = new AdjacencyList(MyListOfVertex.getInstance());
        //scan each vertex in the array to find connections between vertex
        AL.addConnectionInfo();
       /* for(int i=0;i<54;i++){
            Vertex current = AL.AV.get(i);
           System.out.println("*******************");
            while (current.next!=null){
                System.out.print(current.num+"->"+current.next.num+'\n');
                current=current.next;
            }//test the Adjacency List
        }*/
        for(int i=0;i<16;i++) {
            playerButtons[i]=new UButton(i);
        }
        setScroller();

        turnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(flag<3)
                    flag++;
                else
                    flag=0;
                turn.setText("The current player is: "+"Player"+String.valueOf(Controller.flag+1));
            }
        });

        //initialize view

        EventQueue.invokeLater(()->{
            Frame = new myGUIWindow(t,Width,Height);
        });



        for(int i=0;i<4;i++){
            act=0;
            flag=i;
            initialTurnCount++;
            while(players[flag].VillageList.size()!=2){
                System.out.println(flag);
            }
//            try{
//                Thread.sleep(10000);}
//            catch(InterruptedException e){
//                e.printStackTrace();
//
//            }
        }
        act=-1;flag=0;
    }
    //generate a random number from 0 to 18
    public int wormholeDeterminer(){
        Random ran = new Random();
        int i=-1;
        i=ran.nextInt(19);
        return i;
    }
    //add 3 different integers to HashSet which indicate 3 wormholes
    public void HSetAdder(HashSet H){
        while (H.size()!=3){
            H.add(wormholeDeterminer());
        }
    }
    //set the 3 random chosen Hexagon as wormholes
    public void wormholeSetter(Hexagone[] H,HashSet<Integer> HS){
        Iterator<Integer> it = HS.iterator();/*!!!auto-unboxing using <Integer>*/
        while (it.hasNext()){
            int i=it.next();
            H[i].HG.Wormhole=true;
        }
    }
    private void setScroller() {//set the 4 tables with 4 JScrollPanes
        ListT=new ListOfTable(players);
        for(int i=0;i<4;i++)
            Jscrolls[i]=new JScrollPane(ListT.LT[i]);
    }
    public static void addResource(int sum){
        if(sum!=7)
        for(Hexagone h:HEX){
            if(h.HG.diceNum == sum){
                for(int i:h.HG.IndicesPlayer){
                    players[i].resource[h.HG.ResNum-1]+=1;
//                    switch (i){
//                        case (0):
//                            PM.PS[0].resource[h.HG.ResNum]+=1;
//                            break;
//                        case (1):
//                            PM.PS[1].resource[h.HG.ResNum]+=1;
//                            break;
//                        case (2):
//                            PM.PS[2].resource[h.HG.ResNum]+=1;
//                            break;
//                        case (3):
//                            PM.PS[3].resource[h.HG.ResNum]+=1;
//                            break;
//
//                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension Dt = t.getScreenSize();
        int sceneWidth = (int)Dt.getWidth();//width of the window
        int sceneHeight = (int)Dt.getHeight();//height of the window
        String title="Back to Catan";//title of the window
        double side=70;//length of side of each hexagon
        double xC=(double)sceneWidth/2;//the center of the window which is also the center of map
        double yC=(double)sceneHeight/2;
        int m=1;//indicate the map to use

        Controller visualisation=new Controller(xC,yC,side,title,sceneWidth,sceneHeight,m);

        MyListOfVertex.va[2].idPlayer=2;
        MyListOfVertex.va[8].idPlayer=1;
        //AdjacencyListRoads.addRoad(2,2,1);
        AdjacencyListRoads ALR = new AdjacencyListRoads();
        ALR.addRoad(8,9,1);
        ALR.addRoad(2,9,2);
        ALR.addRoad(8,12,1);

    }
}

package com.company;

import inGame.Player;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Controller {
    //data field
    private static Hexagone[] HEX;
     static double xCoord = 0;//the xCenter of center hexagon
     static double yCoord = 0;//the yCenter of center hexagon
     static double side = 0;//the side of each hex
     static int[] map=new int[19];
    public static int mapID=4;//indicate the map to use
    private static HashSet<Integer> HSet = new HashSet<>(3);
    private static AdjacencyList AL ;
    public static AdjacencyListRoads ALR = new AdjacencyListRoads();
    public static Player[] players = new Player[4];
    public static int act= 0;//0 to build village, 1 to build city, 2 to build road
     static int initialTurnCount=0;
     static int initialVillageCount = 0;
    public static int flag = 0; // to identify the current player ranging from 0 to 3
    static boolean wormholeClicked = false;//indicate if one wormhole is clicked
    public static int currentDiceNumber = 0;
    static int lastRoadKing = -1;
    //view field
    private static myGUIWindow Frame;
    //getters
     static Hexagone[] getHEX() {
        return HEX;
    }
     static int[] getMap(){
        return map;
    }
    //constructor
    public Controller(double xC,double yC,double s,String t,int Width,int Height,int m){
        //initialize data
        xCoord=xC;
        yCoord=yC;
        side = s;
        for(int i=0;i<4;i++)
            players[i]=new Player(i);//initialize the 4 players
        HEX=new Hexagone[19];
        map=Maps.mapDeterminer(m);// an array with size of 19
        for(int i=0;i<19;i++){
            HEX[i]=new Hexagone(xCoord,yCoord,side,i);
        }
        if(mapID==4){//wormholes only appear in 1985
            HSetAdder(HSet);
            wormholeSetter(HEX,HSet);
        }
        //scan each vertex of each hexagon to add all vertex to the array without redundancy
        for(int i=0;i<19;i++){
            for(int j=0;j<6;j++){
                MyListOfVertex.getInstance().traverseToAdd(new Vertex(HEX[i].xCoordinates[j],HEX[i].yCoordinates[j],i),i);
            }
        }
        //set number to identify the array index of the Vertex
        for(int i=0;i<MyListOfVertex.va.length;i++){
            MyListOfVertex.va[i].num=i;
        }
        //instance a new Adjacency List with the parameter of the vertex array
        AL = new AdjacencyList();
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

        //initialize view
        EventQueue.invokeLater(()->{
            Frame = new myGUIWindow(t,Width,Height);
                myGUIWindow.canvas.ListT.LT[0].updateUI();
                myGUIWindow.canvas.ListT.LT[1].updateUI();
                myGUIWindow.canvas.ListT.LT[2].updateUI();
                myGUIWindow.canvas.ListT.LT[3].updateUI();
        });
        //The first turn which allows players to build initial 2 villages and 2 roads
        for(int i=0;i<4;i++){
            act=0;
            flag=i;
            initialTurnCount++;
            JOptionPane.showMessageDialog(null, "Player"+(i+1)+" to build 2 free villages in 5s.");
            try{
                Thread.sleep(5000);}
            catch(InterruptedException e){
                e.printStackTrace();
            }
            if(initialVillageCount==2){
                JOptionPane.showMessageDialog(null,"You have built 2 first villages !");
            }
            initialVillageCount=0;
            if(players[flag].VillageList.size()==2) {
                int tmp =2;
            	while(tmp>0) {
					if(tmp==2)
						JOptionPane.showMessageDialog(null, "You can build 2 roads! Now build the first one !");
					else
						JOptionPane.showMessageDialog(null, "You can build 2 roads! Now build the second one !");
					int leftPoint = 0;
					int rightPoint = 0;
					String str = JOptionPane.showInputDialog("Input two numbers separated by ';'");
					if(!"".equals(str)){
						String[] strs = str.split(";|£»");
						if(strs.length==2) {
							if (Integer.parseInt(strs[0]) > Integer.parseInt(strs[1])) {
								leftPoint = Integer.parseInt(strs[1]);
								rightPoint = Integer.parseInt(strs[0]);
							} else {
								leftPoint = Integer.parseInt(strs[0]);
								rightPoint = Integer.parseInt(strs[1]);
							}
							if(AdjacencyList.ifNeighbors(leftPoint,rightPoint)&&AdjacencyListRoads.ifRoadOccupied(leftPoint,rightPoint)&&((AdjacencyList.ifVertexOccupiedByMe(leftPoint,i)||AdjacencyList.ifVertexOccupiedByMe(rightPoint,i))||AdjacencyListRoads.ifConnectedWithARoad(leftPoint,rightPoint,i))){
								Controller.ALR.addRoad(leftPoint,rightPoint,Controller.flag);
								JOptionPane.showMessageDialog(null,"You have built a road!");
								myGUIWindow.canvas.repaint();
							}
							else {
								JOptionPane.showMessageDialog(null,"You cannot build this road, please select again!");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Please enter 2 numbers!");
						}
					}
					else
						JOptionPane.showMessageDialog(null,"Please enter 2 numbers!");
					tmp--;
				}
            }

        }
        //set they back to initial value
        act=-1;flag=0;
    }
    //generate a random number from 0 to 18
    private int wormholeDeterminer(){
        Random ran = new Random();
        int i=ran.nextInt(19);
        return i;
    }
    //add 3 different integers to HashSet which indicate 3 wormholes
    private void HSetAdder(HashSet<Integer> H){
        while (H.size()!=3){
            H.add(wormholeDeterminer());
        }
    }
    //set the 3 random chosen Hexagon as wormholes
    private void wormholeSetter(Hexagone[] H,HashSet<Integer> HS){
        Iterator<Integer> it = HS.iterator();/*!!!auto-unboxing using <Integer>*/
        while (it.hasNext()){
            int i=it.next();
            H[i].HG.Wormhole=true;
        }
    }
    public static void addResource(int sum){
        if(sum!=7)
        for(Hexagone h:HEX){
            if(h.HG.diceNum == sum && !h.HG.Biff){
                for(int i:h.HG.IndicesPlayer){
                    players[i].resource[h.HG.ResNum-1]+=1;
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
        double side=74;//length of side of each hexagon
        double xC=(double)sceneWidth/2;//the center of the window which is also the center of map
        double yC=(double)sceneHeight/2;

        Controller visualisation=new Controller(xC,yC,side,title,sceneWidth,sceneHeight,mapID);
        for(Hexagone hg:HEX){
            if(hg.HG.Wormhole)
                System.out.println(hg.id_fixed_shape);
        }
    }
}

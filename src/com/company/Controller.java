package com.company;

import java.awt.*;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Controller {
    //data field
    private static Hexagone[] HEX;
    private static int[] map=new int[19];
    static HashSet<Integer> HSet = new HashSet<>(3);
    AdjacencyList AL ;
    //view field
    private static myGUIWindow Frame;
    private static myGUIWindow Frame2;
    public static Hexagone[] getHEX() {
        return HEX;
    }
    public static int[] getMap(){
        return map;
    }
    //constructor
    public Controller(double xC,double yC,double s,String t,int Width,int Height,int m){
        //initialize data
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
            MyListOfVertex.va[i].RB = new RoundButton(String.valueOf(i));
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
        //initialize view
        EventQueue.invokeLater(()->{
            Frame = new myGUIWindow(t,Width,Height);
        });
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
    public static void main(String[] args) {
        int sceneWidth =1200;//width of the window
        int sceneHeight=600;//height of the window
        String title="Back to Catan";//title of the window
        double side=70;//length of side of each hexagon
        double xC=(double) sceneWidth/2;//the center of the window which is also the center of map
        double yC=(double)sceneHeight/2;
        int m=2;//indicate the map to use
        Controller visualisation=new Controller(xC,yC,side,title,sceneWidth,sceneHeight,m);

    }
}

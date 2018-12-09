package com.company;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Controller {
    //data field
    private static Hexagone[] HEX;
    private static int[] map=new int[19];
    HashSet<Integer> HSet = new HashSet<>(3);
    //view field
    private static myGUIWindow Frame;
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
}

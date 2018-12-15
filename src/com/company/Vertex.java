package com.company;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class Vertex {
    double xCoord;
    double yCoord;
    boolean village = false;
    boolean city = false;
    Vertex next = null;
    int num;
    RoundButton RB ;
    HashSet<Integer> HexNum = new HashSet<>(3);

    public Vertex(Vertex v){
        xCoord=v.xCoord;
        yCoord=v.yCoord;
        HexNum=v.HexNum;
        num=v.num;
        city=v.city;
        village=v.village;
        next=null;
    }

    public Vertex(double x,double y,int N){
        xCoord=x;
        yCoord=y;
        HexNum.add(N);
        num=-1;
    }
    public boolean equal(Vertex vertex){

        DecimalFormat df = new DecimalFormat("0.00");
        String s1 = df.format(xCoord );
        String s2 = df.format(vertex.xCoord );
        String s3 = df.format(yCoord );
        String s4 = df.format(vertex.yCoord );
//        System.out.println(s1);
//        System.out.println("v:"+s2);
//        System.out.println(s3);
//        System.out.println("v:"+s4);

        if(s1.equals(s2)  && s3.equals(s4))
            return true;
        else
            return false;
    }
    public void adds(int N){
        HexNum.add(N);
    }
}

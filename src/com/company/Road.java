package com.company;

public class Road {
    int[] xCords=new int[2];
    int[] yCords=new int[2];
    int leftPoint;
    int rightPoint;
    Road next ;
    //constructor
    public Road() {
        xCords[0]=0;
        xCords[1]=0;
        yCords[0]=0;
        yCords[1]=0;
        leftPoint=-1;
        rightPoint=-1;
        next=null;
    }
    public Road(int l,int r){
        if(l==r)
            System.out.println("Impossible to draw the line between the same points");
        else if(l<r) {
            leftPoint = l;
            rightPoint = r;
        }
        else {
            leftPoint = r;
            rightPoint = l;
        }
        xCords[0]=(int)MyListOfVertex.va[leftPoint].xCoord;
        yCords[0]=(int)MyListOfVertex.va[leftPoint].yCoord;
        xCords[1]=(int)MyListOfVertex.va[rightPoint].xCoord;
        yCords[1]=(int)MyListOfVertex.va[rightPoint].yCoord;
        next=null;

    }
    public void drawRoad(Vertex v1,Vertex v2){
        xCords[0]=(int)v1.xCoord;
        yCords[0]=(int)v1.yCoord;
        xCords[1]=(int)v2.xCoord;
        yCords[1]=(int)v2.yCoord;
    }

}


package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class AdjacencyList {//the class to store graph info of vertex
    private static final int VexNum = 54;
    static ArrayList<Vertex> AV ;//data structure
    public AdjacencyList(MyListOfVertex m){
        AV = new ArrayList<>(Arrays.asList(m.va));//initialize data with the vertex stored without redundancy
    }
    private double distanceCalculator(Vertex v1,Vertex v2){//calculate distance between 2 vertex
        double d = 0;
        d = Math.sqrt((v1.xCoord-v2.xCoord)*(v1.xCoord-v2.xCoord)+(v1.yCoord-v2.yCoord)*(v1.yCoord-v2.yCoord));
        return d;
    }
    public void addConnectionInfo(){//construct the graph
        Vertex current = null;//pointer to help construct this graph
        for(int i=0;i<VexNum;i++){//for each vertex
            current=AV.get(i);
            for(int j=0;j<VexNum;j++){//calculate the distance between itself and all the rest vertex
                int d=(int)distanceCalculator(AV.get(i),AV.get(j));
                if(d>=69 && d<=71){//important!!!the side is 70
                    current.next = new Vertex(AV.get(j));//add a node which is the next vertex
                }
                while (current.next!=null)//go on to the latest node
                    current=current.next;
            }
        }
    }
    public static boolean ifNeighbors(int a ,int b){
        Vertex current = AV.get(a);
        while(current.next!=null){
            if (current.next.num==b)
                return true;
            current=current.next;
        }
        return false;
    }
    public static boolean ifVertexOccupiedByMe(int a,int idP){
        if(MyListOfVertex.va[a].idPlayer==idP){
            return true;
        }
        else
            return false;

    }
}

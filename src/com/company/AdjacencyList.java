package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class AdjacencyList {
    static final int VexNum = 54;
    private Vertex current = null;
    ArrayList<Vertex> AV ;
    public AdjacencyList(MyListOfVertex m){
        AV = new ArrayList<>(Arrays.asList(m.va));

    }
    private double distanceCalculator(Vertex v1,Vertex v2){
        double d = 0;
        d = Math.sqrt((v1.xCoord-v2.xCoord)*(v1.xCoord-v2.xCoord)+(v1.yCoord-v2.yCoord)*(v1.yCoord-v2.yCoord));
        return d;
    }
    public void addConnectionInfo(){
        for(int i=0;i<VexNum;i++){
            current=AV.get(i);
            for(int j=0;j<VexNum;j++){
                int d=(int)distanceCalculator(AV.get(i),AV.get(j));
                if(d>=69 && d<=71){
                    current.next = new Vertex(AV.get(j));
                }
                while (current.next!=null)
                    current=current.next;
            }
        }
    }
}

package com.company;

import javax.swing.*;

public class AdjacencyListRoads {
    static Road[] RArray = new Road[4];//4 indicates four players, the adjacency list has head nodes
     AdjacencyListRoads(){
        for(int i=0;i<4;i++){
            RArray[i]=new Road();
        }
    }
    public  void addRoad(int left,int right,int idPlayer){
        if(AdjacencyList.ifNeighbors(left,right)&&ifRoadOccupied(left, right)&&((AdjacencyList.ifVertexOccupiedByMe(left,idPlayer)||AdjacencyList.ifVertexOccupiedByMe(right,idPlayer))||ifConnectedWithARoad(left,right,idPlayer))){
            Road current = RArray[idPlayer];
            while (current.next!=null)
                current=current.next;
            current.next = new Road(left,right);
            JOptionPane.showMessageDialog(null,"Successfully built!");
            myGUIWindow.canvas.repaint();
        }
        else
            JOptionPane.showMessageDialog(null,"You cannot build this road!");
    }
    public static boolean ifRoadOccupied(int left,int right){
        Road current;
        for(int i=0;i<4;i++){
            current=RArray[i];
            while (current.next!=null){
                if(current.next.leftPoint==left&&current.next.rightPoint==right)
                    return false;
                current=current.next;
            }
        }
        return true;
    }
    public static boolean ifConnectedWithARoad(int a,int b,int idP){
        Road current = RArray[idP];
        while (current.next!=null){
            if(current.next.leftPoint==a||current.next.leftPoint==b||current.next.rightPoint==a||current.next.rightPoint==b)
                return true;
            current=current.next;
        }
        return false;
    }
    static int roadKingDeterminer(){
        int max= 0;
        int player = -1;
        for(int i=0;i<4;i++){
            int m=0;
            Road current = RArray[i];
            while (current.next!=null){
                current=current.next;
                m++;
            }
            if(m>max){
                max=m;
                player=i;
            }
        }
        if(max>=5)
            return player;
        else
            return -1;
    }
}

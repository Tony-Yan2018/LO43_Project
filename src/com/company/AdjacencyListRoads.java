package com.company;

public class AdjacencyListRoads {
    static Road[] RArray = new Road[4];
    public AdjacencyListRoads(){
        for(int i=0;i<4;i++){
            RArray[i]=new Road();
        }
    }
    protected  void addRoad(int left,int right,int idPlayer){
        if(AdjacencyList.ifNeighbors(left,right)&&ifRoadOccupied(left, right)&&((AdjacencyList.ifVertexOccupiedByMe(left,idPlayer)||AdjacencyList.ifVertexOccupiedByMe(right,idPlayer))||ifConnectedWithARoad(left,right,idPlayer))){
            Road current = RArray[idPlayer];
            while (current.next!=null)
                current=current.next;
            current.next = new Road(left,right);
        }
        else
            System.out.println("cannot construct this road");
    }
    private  boolean ifRoadOccupied(int left,int right){
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
    private  boolean ifConnectedWithARoad(int a,int b,int idP){
        Road current = RArray[idP];
        while (current.next!=null){
            if(current.next.leftPoint==a||current.next.leftPoint==b||current.next.rightPoint==a||current.next.rightPoint==b)
                return true;
            current=current.next;
        }
        return false;
    }
}

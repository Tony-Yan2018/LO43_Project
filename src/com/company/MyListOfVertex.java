package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class MyListOfVertex {
    /*data*/
    static int realSize=0;
    static Vertex[] va = new Vertex[54] ;
    /*singleton*/
    private MyListOfVertex(){
        for(int i=0;i<54;i++){//must initialize the self-defined class array
            va[i] = new Vertex(-1,-1,-1);
        }
    };
    private static MyListOfVertex MV = new MyListOfVertex();
    public static MyListOfVertex getInstance(){
        return MV;
    }

    /*methods*/
    public void traverseToAdd(Vertex v,int n){
        boolean flag = false;//indicate if there is already the same point
        for (Vertex vm:va){//has the same point
            if(vm.equal(v)){
                vm.adds(n);//add hex info to the existing point
                flag=true;
                break;
            }
        }
        //no same point
        if(!flag){
            //let's add it !
            //System.out.println("***************************:"+realSize);
            va[realSize]=v;
            realSize++;
        }
    }
}

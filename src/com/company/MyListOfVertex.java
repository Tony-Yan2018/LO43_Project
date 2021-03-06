package com.company;

 class MyListOfVertex {
    /*data*/
    private static int realSize=0;
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
     void traverseToAdd(Vertex v,int n){
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
            va[realSize]=v;
            realSize++;
        }
    }
}

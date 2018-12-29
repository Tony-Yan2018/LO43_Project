package com.company;

public class Maps {
    private Maps(){}//instance forbidden
    //the maps of four eras
    public static final int[] mapColorRes_1={3,1,2,3,2,4,1,2,4,1,2,3,1,2,4,0,4,3,2};
    public static final int[] mapColorRes_2={2,3,1,4,3,4,3,1,2,0,2,1,4,3,2,4,2,1,2};
    public static final int[] mapColorRes_3={2,3,4,1,0,4,2,2,3,2,1,4,2,1,4,3,3,2,1};
    public static final int[] mapColorRes_4={1,3,4,1,2,0,2,2,4,3,1,2,1,3,2,4,4,2,3};

    //map chooser
    public static int[] mapDeterminer(int mapNum){
        int[] m=new int[19];
        if(mapNum>=1&&mapNum<=4){
            switch (mapNum){
                case 1: m=mapColorRes_1;
                    break;
                case 2: m=mapColorRes_2;
                    break;
                case 3: m=mapColorRes_3;
                    break;
                case 4: m=mapColorRes_4;
                    break;
            }
        }
        return m;
    }
}

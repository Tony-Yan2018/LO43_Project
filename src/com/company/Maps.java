package com.company;

public class Maps {
    private Maps(){}//instance forbidden
    //the maps of four eras
    private static final int[] mapColorRes_1={3,1,2,3,2,4,1,2,4,1,2,3,1,2,4,0,4,3,2};//1885
    private static final int[] mapColorRes_2={2,3,1,4,3,4,3,1,2,0,2,1,4,3,2,4,2,1,2};//1905
    private static final int[] mapColorRes_3={2,3,4,1,0,4,2,2,3,2,1,4,2,1,4,3,3,2,1};//2015
    private static final int[] mapColorRes_4={1,3,4,1,2,0,2,2,4,3,1,2,1,3,2,4,4,2,3};//1985
    static int year=1985;
    //map chooser
     static int[] mapDeterminer(int mapNum){
        int[] m=new int[19];
        if(mapNum>=1&&mapNum<=4){
            switch (mapNum){
                case 1: m=mapColorRes_1;
                year=1885;
                    break;
                case 2: m=mapColorRes_2;
                year=1905;
                    break;
                case 3: m=mapColorRes_3;
                year=2015;
                    break;
                case 4: m=mapColorRes_4;
                year=1985;
                    break;
            }
        }
        return m;
    }
}

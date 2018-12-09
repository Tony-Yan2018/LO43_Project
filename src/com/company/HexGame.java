package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HexGame {

    Color ColorRes;//determine the color to display according to its resource
    boolean Biff;//indicate if Biff is on this hex
    boolean Wormhole;//indicate if this hex has a wormhole
    int[] IndicesPlayer =new int[6];//indicate players of the buildings on the 6 vertex
    private int ResNum;//indicate the resource the hex belong to from 0 to 4
    int diceNum;//the dice number to display according to its resource
    /*
    * dice numbers assigned to each resource
    * */
    public static  ArrayList<Integer> pink_4=new ArrayList<>(Arrays.asList(2,12,3,11));
    public static  ArrayList<Integer> yellow_6=new ArrayList<>(Arrays.asList(4,9,3,6,8,5));
    public static  ArrayList<Integer> cyan_4=new ArrayList<>(Arrays.asList(10,6,4,11));
    public static  ArrayList<Integer> orange_4=new ArrayList<>(Arrays.asList(10,9,8,5));
    public static boolean[] wormhole = {true,true,true};
    public HexGame(int id_Hex){
        setColorRes(Controller.getMap()[id_Hex]);
        setBiff(Controller.getMap()[id_Hex]);
        diceNum=diceNumPicker(ResNum);

    }

    public void setColorRes(int colorRes) {
        switch (colorRes){
            case 0:ColorRes=Color.lightGray;//color of Biff
                ResNum=0;
            break;
            case 1:ColorRes=Color.pink;//color of mineral resources
                ResNum=1;
            break;
            case 2:ColorRes=Color.yellow;//color of grains
                ResNum=2;
            break;
            case 3:ColorRes=Color.cyan;//color of textiles
                ResNum=3;
            break;
            case 4:ColorRes=Color.orange;//color of building materials
                ResNum=4;
            break;
        }
    }
    public void setBiff(int colorRes){
        if (colorRes==0)
            Biff=true;
        else
            Biff=false;
    }
    public int getAndRemove(ArrayList<Integer> A){
        Random r=new Random();
        int i=r.nextInt(A.size());//generate a random index within the range of the array
        int j=A.get(i);
        A.remove(i);//remove the used number from array
        return j;
    }
    public int diceNumPicker(int ResNum){
        int dice=-1;
        switch (ResNum){
            case 0: dice=7;
            break;
            case 1: dice=getAndRemove(pink_4);
            break;
            case 2: dice=getAndRemove(yellow_6);
            break;
            case 3: dice=getAndRemove(cyan_4);
            break;
            case 4: dice=getAndRemove(orange_4);
            break;
        }
        return dice;
    }

}

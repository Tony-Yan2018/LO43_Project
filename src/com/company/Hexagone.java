package com.company;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.*;

public class Hexagone {
    /* attributes*/
    double xCenter,yCenter;
    double side;
    double[] xCoordinates=new double[6];
    double[] yCoordinates=new double[6];
    int id_fixed_shape;
    public JLabel diceRes;
    HexGame HG;
     /*constructor*/
    public Hexagone(double x,double y,double s,int id){
        xCenter=x;
        yCenter=y;
        side=s;
        id_fixed_shape=id;
        CenterCalculator(id);
        CoordinateCalculator();
        HG=new HexGame(id);
        diceRes=new JLabel(String.valueOf(HG.diceNum));
        MouseListener ML = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Hexagone.this.HG.Wormhole) {
                    diceRes.setText("Oops!");
                    Controller.wormholeClicked=true;
                }
                if(Controller.currentDiceNumber==7){
                    for(int i=0;i<19;i++){
                        if(Controller.getHEX()[i].HG.Biff){
                            Controller.getHEX()[i].HG.Biff=false;
                        }
                    }
                    Hexagone.this.HG.Biff=true;
                    myGUIWindow.updateJTables();
                    myGUIWindow.canvas.repaint();
                }
            }};
        diceRes.addMouseListener(ML);
    }
//    public Hexagone(int id){
//        HG=new HexGame(id);
//        diceRes=new JLabel(String.valueOf(HG.diceNum));
//        MouseListener ML = new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(Hexagone.this.HG.Wormhole)
//                    diceRes.setText("Oops!");
//            }};
//        diceRes.addMouseListener(ML);
//    }
//    public Hexagone(Hexagone h){
//        HG=h.HG;
//        diceRes=h.diceRes;
//        MouseListener ML = new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(Hexagone.this.HG.Wormhole)
//                    diceRes.setText("Oops!");
//            }};
//        diceRes.addMouseListener(ML);
//
//    }
    private void CenterCalculator(int id_fixed_shape){
        switch (id_fixed_shape){
            case 0: xCenter=xCenter-sqrt(3)*side;
                    yCenter=yCenter-3*side;
                    break;
            case 1: xCenter=xCenter;
                    yCenter=yCenter-3*side;
                    break;
            case 2: xCenter=xCenter+sqrt(3)*side;
                    yCenter=yCenter-3*side;
                    break;
            case 3: xCenter=xCenter-3*sqrt(3)*side/2;
                    yCenter=yCenter-3*side/2;
                    break;
            case 4: xCenter=xCenter-sqrt(3)*side/2;
                    yCenter=yCenter-3*side/2;
                    break;
            case 5: xCenter=xCenter+sqrt(3)*side/2;
                    yCenter=yCenter-3*side/2;
                    break;
            case 6: xCenter=xCenter+3*sqrt(3)*side/2;
                    yCenter=yCenter-3*side/2;
                    break;
            case 7: xCenter=xCenter-2*sqrt(3)*side;
                    yCenter=yCenter;
                    break;
            case 8: xCenter=xCenter-sqrt(3)*side;
                    yCenter=yCenter;
                    break;
            case 9: xCenter=xCenter;
                    yCenter=yCenter;
                    break;
            case 10: xCenter=xCenter+sqrt(3)*side;
                    yCenter=yCenter;
                    break;
            case 11: xCenter=xCenter+2*sqrt(3)*side;
                    yCenter=yCenter;
                    break;
            case 12: xCenter=xCenter-3*sqrt(3)*side/2;
                    yCenter=yCenter+3*side/2;
                    break;
            case 13: xCenter=xCenter-sqrt(3)*side/2;
                    yCenter=yCenter+3*side/2;
                    break;
            case 14: xCenter=xCenter+sqrt(3)*side/2;
                    yCenter=yCenter+3*side/2;
                    break;
            case 15: xCenter=xCenter+3*sqrt(3)*side/2;
                    yCenter=yCenter+3*side/2;
                    break;
            case 16: xCenter=xCenter-sqrt(3)*side;
                    yCenter=yCenter+3*side;
                    break;
            case 17: xCenter=xCenter;
                    yCenter=yCenter+3*side;
                    break;
            case 18: xCenter=xCenter+sqrt(3)*side;
                    yCenter=yCenter+3*side;
                    break;
        }
    }
    private void CoordinateCalculator(){
        xCoordinates[0]=xCenter;
        yCoordinates[0]=yCenter-side;
        xCoordinates[1]=xCenter+ sqrt(3)*side/2;
        yCoordinates[1]=yCenter-side/2;
        xCoordinates[2]=xCenter+ sqrt(3)*side/2;
        yCoordinates[2]=yCenter+side/2;
        xCoordinates[3]=xCenter;
        yCoordinates[3]=yCenter+side;
        xCoordinates[4]=xCenter- sqrt(3)*side/2;
        yCoordinates[4]=yCenter+side/2;
        xCoordinates[5]=xCenter- sqrt(3)*side/2;
        yCoordinates[5]=yCenter-side/2;
    }
}

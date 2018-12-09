package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        int sceneWidth =1200;//width of windows
        int sceneHeight=600;//height of windows
        String title="Back to Catan";//title of windows
        double side=70;//length of side
        double xC=(double)(1200/2);//the center of the windows which is also the center of map
        double yC=(double)(600/2);
        int m=2;//indicate the map to use
        Controller visualisation=new Controller(xC,yC,side,title,sceneWidth,sceneHeight,m);

}}

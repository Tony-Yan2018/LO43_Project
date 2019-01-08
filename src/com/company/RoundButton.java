package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.*;

public class RoundButton extends JButton {
    private int idButton;
    private boolean clicked = false;
    private int count = 0;
    private Color color =Color.WHITE;
    public RoundButton(String s,int n) {
        super(s);
        idButton=n;
        setFont(new Font("Times New Roman",Font.CENTER_BASELINE,11));
        //allows to draw our own background instead of auto-filling the bounding box
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            		//Controller.players[0].resource[0]+=10;
	                if(Controller.act==0&&!MyListOfVertex.va[idButton].village) {
	                    clicked = true;
	                    if(Controller.initialTurnCount<=4&&Controller.initialVillageCount<2) {
		                    color=Controller.players[Controller.flag].color;
		                    Controller.players[Controller.flag].score+=1;
		                    Controller.initialVillageCount ++;
                            updateInfo(++count);
	                    }
	                    if(Controller.initialTurnCount>4) {
		                    Controller.act=-1;
		                    color=Controller.players[Controller.flag].color;
		                    Controller.players[Controller.flag].score+=1;
                            updateInfo(++count);
	                    }
	                }
	                else if(Controller.act==1&&MyListOfVertex.va[idButton].village&&!MyListOfVertex.va[idButton].city){
	                    Controller.act=-1;
	                    Controller.players[Controller.flag].score+=2;
                        updateInfo(++count);
	                }
	            }
            
            
        });
    }
    protected void paintComponent(Graphics g) {
        if(clicked&&count==1) {//make sure that we are building a village
            g.setColor(color);
            setForeground(Color.white);
        }
        else if(clicked&&count==2){
            g.setColor(color);
        }
        else {
            g.setColor(Color.white);
            setForeground(Color.black);
        }
        g.fillOval(1, 1, 40, 40);
        super.paintComponent(g);//number string does not display without this line
    }
    // paint the bounding circle of the button
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.8f));
        g2d.drawOval(1, 1, 40, 40);
        if(clicked&&count==2){
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2.8f));
            g2d.drawOval(11, 11, 20, 20);
        }
    }
    private void updateInfo(int count){
        switch (count){
            case (1):  //when building a village
                MyListOfVertex.va[idButton].village=true;
                MyListOfVertex.va[idButton].idPlayer = Controller.flag;
                HashSet<Integer> HS = MyListOfVertex.va[idButton].HexNum;
                Iterator<Integer> it = HS.iterator();
                while(it.hasNext()){//add player info to each hex
                   Controller.getHEX()[it.next()].HG.IndicesPlayer.add(Controller.flag);
                }
                Controller.players[Controller.flag].VillageList.add(idButton);//add village info to player
                break;
            case (2):
                MyListOfVertex.va[idButton].village=false;
                MyListOfVertex.va[idButton].city=true;
                Controller.players[Controller.flag].VillageList.remove(idButton);
                Controller.players[Controller.flag].CityList.add(idButton);
                break;
            case (3):
                MyListOfVertex.va[idButton].city=false;
                MyListOfVertex.va[idButton].village=false;
                break;
    }
    }
}


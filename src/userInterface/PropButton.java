package userInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class PropButton{
	
	public int xcoord;
	public int ycoord;
	public int xsize;
	public int ysize;
	public String name;
	PropButton(){xcoord=0;ycoord=0;xsize=0;ysize=0;name="null";}
	PropButton(int x,int y,int xs,int ys){
		xcoord=x;ycoord=y;xsize=xs;ysize=ys;name="null";
	}
	void setName(String n) {
		name=n;
	}
	
	
}

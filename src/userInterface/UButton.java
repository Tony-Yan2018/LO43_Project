package userInterface;
import com.company.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UButton extends JButton {
	public int xcoord;
	public int ycoord;
	public int xsize;
	public int ysize;
	int idAct;
	int playerID;
	public UButton(int IDAct) {
		idAct=IDAct;
		xsize=100;
		ysize=50;
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension Dt = t.getScreenSize();
		if(IDAct>=0&&IDAct<4) {
			playerID = 0;
			xcoord=IDAct*100;
			ycoord=105;
		}
		else if(IDAct>=4&&IDAct<8) {
			playerID = 1;
			xcoord=(IDAct-4)*100;
			ycoord=(int)Dt.getHeight()-105;
		}
		else if(IDAct>=8&&IDAct<12) {
			playerID = 2;
			xcoord=(int)Dt.getWidth()-400+(IDAct-8)*100;
			ycoord=105;
		}
		else{
			playerID = 3;
			xcoord=(int)Dt.getWidth()-400+(IDAct-12)*100;
			ycoord=(int)Dt.getHeight()-105;
		}
		if(idAct==0||idAct==4||idAct==8||idAct==12){
			setText("Trade");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//trade
				final TradeFrame TF=new TradeFrame("Trade",1);
			}
		});}
		else if(idAct==1||idAct==5||idAct==9||idAct==13) {
			setText("construct");
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					//trade
					final ConstructFrame TF=new ConstructFrame(2);
				}
			});
		}
		else if(idAct==2||idAct==6||idAct==10||idAct==14){
			setText("Get Card");
			addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//trade
				final GetCardFrame TF=new GetCardFrame(3);
			}
		});}
		else{
			setText("Use Card");
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					final UseCardFrame UCF = new UseCardFrame(playerID);
				}
			});
		}

	}
	
	
}

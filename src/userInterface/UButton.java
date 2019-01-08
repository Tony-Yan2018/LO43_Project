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
	private int playerID;
	public UButton(int IDAct) {
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
		if(IDAct ==0|| IDAct ==4|| IDAct ==8|| IDAct ==12){
			setText("Trade");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//trade
				final TradeFrame TF=new TradeFrame("Trade",playerID);
			}
		});}
		else if(IDAct ==1|| IDAct ==5|| IDAct ==9|| IDAct ==13) {
			setText("Construct");
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					final ConstructFrame TF=new ConstructFrame(playerID);
				}
			});
		}
		else if(IDAct ==2|| IDAct ==6|| IDAct ==10|| IDAct ==14){
			setText("Get Card");
			addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final GetCardFrame TF=new GetCardFrame(playerID);
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

package userInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UButton extends JButton {
	int idAct;
	public UButton(String n,int ID) {
		super(n);
		idAct=ID;
		if(idAct==0||idAct==4||idAct==8||idAct==12)
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//trade
				final TradeFrame TF=new TradeFrame("name",1);
			}
		});
		else if(idAct==1||idAct==5||idAct==9||idAct==13) {
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					//trade
					final ConstructFrame TF=new ConstructFrame("name",2);
				}
			});
		}
		else if(idAct==2||idAct==6||idAct==10||idAct==14)
			addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//trade
				final GetCardFrame TF=new GetCardFrame("name",3);
			}
		});
			
	}
	
	
}

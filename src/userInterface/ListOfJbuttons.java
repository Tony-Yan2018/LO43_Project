package userInterface;

import java.awt.*;

public class ListOfJbuttons {
	static public PropButton[] Pb=new PropButton[16];
	private ListOfJbuttons() {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension Dt = t.getScreenSize();
		int i;
		for(i=0;i<4;i++)
			Pb[i]=new PropButton(0+i*100,100,75,50);
		for(i=4;i<8;i++)
			Pb[i]=new PropButton(0+(i-4)*100,(int)Dt.getHeight()-100,100,50);
		for(i=8;i<12;i++)
			Pb[i]=new PropButton((int)Dt.getWidth()-400+(i-8)*100,100,100,50);
		for(i=12;i<16;i++)
			Pb[i]=new PropButton((int)Dt.getWidth()-400+(i-12)*100,(int)Dt.getHeight()-100,100,50);
		for(i=0;i<16;i+=4)
			Pb[i].setName("Trade");
		for(i=1;i<16;i+=4)
			Pb[i].setName("construct");
		for(i=2;i<16;i+=4)
			Pb[i].setName("get card");
		for(i=3;i<16;i+=4)
			Pb[i].setName("use card");
	}
	private static ListOfJbuttons LJ=new ListOfJbuttons();
}

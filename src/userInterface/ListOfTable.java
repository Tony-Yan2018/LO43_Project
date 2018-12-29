package userInterface;

import inGame.Player;

import java.awt.*;

public class ListOfTable {
	static public int[][] coord;
	
	Object[] columnNames = {"Res|Time",  "1985"};
	public MyTable[] LT= new MyTable[4];
	public ListOfTable() {
		LT=null;
		coord=new int[][] {
			{0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
		};
	}
	public ListOfTable(Player[] p) {
		for(int i=0;i<4;i++)
		{
			LT[i]=new MyTable(p[i].ressource,columnNames);
		}
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension Dt = t.getScreenSize();
		coord=new int[][] {
			{0, 0, 200, 100},
            {0, (int)Dt.getHeight()-200, 200, 100},
            {(int)Dt.getWidth()-200, 0, 200, 100},
            {(int)Dt.getWidth()-200, (int)Dt.getHeight()-200, 200, 100}
		};
	}
}

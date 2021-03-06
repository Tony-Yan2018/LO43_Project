package userInterface;

import inGame.Player;

import javax.swing.*;
import java.awt.*;

public class ListOfTable {
	static public int[][] coord;
	public MyTable[] LT= new MyTable[4];
	public ListOfTable(Player[] p) {
		for (int i = 0; i < 4; i++){
			Object[][] rowData = new Object[][]{
					{"Silver", p[i].resource[0]},
					{"Rice", p[i].resource[1]},
					{"Wool", p[i].resource[2]},
					{"Cement", p[i].resource[3]},
					{"Score", p[i].score},
					{"Harvest Card", p[i].harvestCard},
					{"Road Card", p[i].roadCard},
					{"Score Card", p[i].currentScoreCard()}
			};
		LT[i] = new MyTable(rowData, new Object[]{"Player" + (i + 1), "1985"});
	}
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension Dt = t.getScreenSize();
		coord=new int[][] {
			{0, 0, 200, 105},
            {0, (int)Dt.getHeight()-210, 200, 105},
            {(int)Dt.getWidth()-200, 0, 200, 105},
            {(int)Dt.getWidth()-200, (int)Dt.getHeight()-210, 200, 105}
		};
	}
}

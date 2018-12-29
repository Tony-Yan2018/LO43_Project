package inGame;

import com.company.Controller;

import userInterface.MyTable;

public class PlayerManager {
	Object[] columnNames = {"Res|Time", "1955", "1985", "2005", "2015"};
	static public Player[] PS;
	public PlayerManager() {
		PS=new Player[4];
		for(int i=0;i<4;i++)
			PS[i]=new Player();
	}
	public void tradePlayers(int Res1,int Res2,int Q1,int Q2,int p1,int p2) {
		PS[p1].ressource[Res1][2]=(int)(PS[p1].ressource[Res1][2])-Q1;
		PS[p2].ressource[Res1][2]=(int)(PS[p1].ressource[Res2][2])+Q1;
		PS[p1].ressource[Res2][2]=(int)(PS[p1].ressource[Res2][2])-Q2;
		PS[p2].ressource[Res2][2]=(int)(PS[p2].ressource[Res2][2])+Q2;
		Controller.ListT.LT[p2]=new MyTable(PS[p2].ressource,columnNames);
		Controller.ListT.LT[p1]=new MyTable(PS[p1].ressource,columnNames);
	}
}

package inGame;

import com.company.Controller;

public class Player {
	static int VillageList=0;
	static int CityList=0;
	static int RoadList=0;
	int[] Cardlist;
	int CardNum;
	public static Object [][] ressource=new Object[5][4];
	public Player() {
		ressource=new Object[][]{
				{"Mineral", 0, 0, 0, 0},
	            {"Food", 0, 0, 0, 0},
	            {"Textile", 0, 0, 0, 0},
	            {"Material", 0, 0, 0, 0}
	            };
	Cardlist=new int[10];
	CardNum=0;
	}
	public boolean Construct(Object TypeConstruct,int res1,int res2,int res3,int res4,int q1,int q2,int q3,int q4) {
		int sum =res1*q1+res2*q2+res3*q3+res4*q4;
		if(TypeConstruct=="Village")
			if(sum>12)
			{
				useRes(res1,res2,res3,res4,q1,q2,q3, q4);
				VillageList++;
				return true;
			}else return false;
		else if(TypeConstruct=="City")
			if(sum>15)
			{
				useRes(res1,res2,res3,res4,q1,q2,q3, q4);
				CityList++;
				return true;
			}else return false;
		else if(TypeConstruct=="Road")
			if(sum>9)
			{
				useRes(res1,res2,res3,res4,q1,q2,q3, q4);
				RoadList++;
				return true;
			}else return false;
		else return false;
	}
	void useRes(int res1,int res2,int res3,int res4,int q1,int q2,int q3,int q4) {
		ressource[res1][1]=(int)ressource[res1][1]-q1;
		ressource[res2][1]=(int)ressource[res2][1]-q2;
		ressource[res3][1]=(int)ressource[res3][1]-q3;
		ressource[res4][1]=(int)ressource[res4][1]-q4;
	}
	public void getCard(int res1,int res2,int res3,int res4,int q1,int q2,int q3,int q4) {
		int sum =res1*q1+res2*q2+res3*q3+res4*q4;
		if(sum>=12)
			{useRes(res1,res2,res3,res4,q1,q2,q3, q4);
			Cardlist[CardNum]=(int) ((Math.random())*10);
			CardNum++;
			}
	}

}

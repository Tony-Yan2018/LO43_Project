package inGame;

import com.company.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	public ArrayList<Integer> VillageList=new ArrayList<>();//serial number of vertex which is integer
	public ArrayList<Integer> CityList=new ArrayList<>();//serial number of vertex which is integer
	public ArrayList<Road> RoadList=new ArrayList<>();
	//ArrayList<DevelopCard> CardList=new ArrayList<>();
	public Integer[] resource =new Integer[]{0,0,0,0};
	int num;//ID of the player ranging from 0 to 3
	public int score=0;
	public boolean roadKing = false;
	public boolean scoreCard = false;
	public int scoreCardCount=0;
	public int harvestCard = 0;
	public int roadCard = 0;
	public Color color;

	public Player(int i){
		num = i;
		switch (num){
			case (0):
				color=Color.RED;
				break;
			case (1):
				color=Color.BLUE;
				break;
			case(2):
				color=Color.GREEN;
				break;
			case (3):
				color=Color.magenta;
		}

	}


	public boolean Construct(String TypeConstruct,int res1,int res2,int res3,int res4,int q1,int q2,int q3,int q4) {
		int sum =res1*q1+res2*q2+res3*q3+res4*q4;
		switch (TypeConstruct){
			case("Village"):
			if(sum>12){
				useRes(q1,q2,q3, q4);
				return true;
			}
			else
				return false;
			case("City"):
			if(sum>15){
				useRes(q1,q2,q3, q4);
				return true;
			}
			else
				return false;
			case("Road"):
			if(sum>9){
				useRes(q1,q2,q3, q4);
				return true;
			}
			else
				return false;
			default:
				return false;
		}
	}
	public void useRes(int q1,int q2,int q3,int q4) {
		resource[0]-=q1;
		resource[1]-=q2;
		resource[2]-=q3;
		resource[3]-=q4;
	}
	public void getCard(int res1,int res2,int res3,int res4,int q1,int q2,int q3,int q4) {
		int sum =res1*q1+res2*q2+res3*q3+res4*q4;
		if(sum>=12) {
			useRes(q1,q2,q3, q4);
			Random ran = new Random();
			int i=ran.nextInt(3-1)+1;
			switch (i){
				case(1):
					harvestCard+=1;
					break;
				case (2):
					roadCard+=1;
					break;
				case (3):
					scoreCard=true;
					scoreCardCount++;
					break;
			}
			}
	}
	public void useCard(int cardNum){
		switch (cardNum){
			case (1)://use harvest card
				for(int i=0;i<3;i++){
					Random ran = new Random();
					int s=ran.nextInt(4);
					resource[s]++;
				}
				harvestCard--;
				break;
			case (2)://use road card
				break;
			case(3)://use score card
				scoreCard = false;
				scoreCalculator();
				break;

		}
	}
	private void scoreCalculator(){
		int villageS = VillageList.size();
		int cityS = CityList.size()*2;
		int sum=0;
		if(roadKing)
			sum = villageS+cityS+scoreCardCount*3+2;
		else
			sum=villageS+cityS+scoreCardCount*3;
		score=sum;
	}
	public int currentScoreCard(){
		if(scoreCard)
			return 1;
		else
			return 0;
	}

}

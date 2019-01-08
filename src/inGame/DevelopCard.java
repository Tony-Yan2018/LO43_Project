package inGame;

public class DevelopCard {
    private static final String Card1 = "Good Harvest";
    private static final String Card2 = "Roads";
    private static final String Card3 = "Scores";
    private String nameOfCard;
    public DevelopCard(int Ran){
        switch (Ran){
            case(1):
                nameOfCard = Card1;
                break;
            case(2):
                nameOfCard = Card2;
                break;
            case (3):
                nameOfCard = Card3;
                break;
        }
    }
}

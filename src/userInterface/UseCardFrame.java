package userInterface;

import com.company.Controller;
import com.company.Hexagone;
import com.company.Maps;
import com.company.myGUIWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UseCardFrame extends JFrame {
    JPanel useCard;
    JButton bt1;
    JButton bt2;
    JButton bt3;
    JButton bt4;
    JLabel LB1;
    int playerID;
    public UseCardFrame(int pID){
        super("Use Card");
        playerID=pID;
        useCard = new JPanel();
        setContentPane(useCard);
        setVisible(true);
        setLayout(null);
        setSize(600,500);
        useCard.setLayout(null);

        bt1 = new JButton("Harvest Card: "+ Controller.players[playerID].harvestCard);
        bt2 = new JButton("Road Card: "+Controller.players[playerID].roadCard);
        bt3 = new JButton("Score Card: "+Controller.players[playerID].currentScoreCard());
        bt4 = new JButton("Get back to 1985!!!");
        LB1 = new JLabel("Use your cards or your time machine");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.players[playerID].harvestCard==0)
                    JOptionPane.showMessageDialog(null,"No more Harvest Card");
                else {
                    Controller.players[playerID].useCard(1);
                    setName("Harvest Card: "+Controller.players[playerID].harvestCard);
                }
            }
        });
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.players[playerID].roadCard==0)
                    JOptionPane.showMessageDialog(null,"No more Road Card");
                else{
                    Controller.players[playerID].useCard(2);
                    setName("Road Card: "+Controller.players[playerID].roadCard);
                }
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Controller.players[playerID].scoreCard)
                    JOptionPane.showMessageDialog(null,"No more Score Card");
                else {
                    Controller.players[playerID].useCard(3);
                    setName("Score Card: "+0);
                }
            }
        });
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.players[Controller.flag].timeMachine && Controller.mapID!=4){
                    Controller.players[Controller.flag].timeMachine=false;
                    Controller.mapID=4;
                    Controller.map= Maps.mapDeterminer(Controller.mapID);
                    for(int i=0;i<19;i++){
                        myGUIWindow.canvas.remove(Controller.getHEX()[i].diceRes);
                    }
                    for(int i=0;i<19;i++){
                        Controller.getHEX()[i]=new Hexagone(Controller.xCoord,Controller.yCoord,Controller.side,i);
                    }
                    for(int i=0;i<19;i++){
                        myGUIWindow.canvas.add(Controller.getHEX()[i].diceRes);
                    }
                    //                System.out.println(HEX[0].diceRes.getText());
                    myGUIWindow.canvas.validate();
                    myGUIWindow.canvas.updateUI();
                    myGUIWindow.canvas.repaint();
                }
            }
        });
        useCard.add(bt1);
        useCard.add(bt2);
        useCard.add(bt3);
        useCard.add(bt4);
        useCard.add(LB1);
        bt1.setBounds(20,200,160,30);
        bt2.setBounds(220,200,160,30);
        bt3.setBounds(420,200,160,30);
        if(Controller.players[Controller.flag].timeMachine) {
            bt4.setBounds(220, 300, 160, 30);
        }
        LB1.setBounds(220,100,500,30);

    }

}

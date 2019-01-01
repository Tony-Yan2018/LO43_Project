package userInterface;

import com.company.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseCardFrame extends JFrame {
    JPanel useCard;
    JButton bt1;
    JButton bt2;
    JButton bt3;
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
        LB1 = new JLabel("Use your cards");
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
        useCard.add(bt1);
        useCard.add(bt2);
        useCard.add(bt3);
        useCard.add(LB1);
        bt1.setBounds(20,200,160,30);
        bt2.setBounds(220,200,160,30);
        bt3.setBounds(420,200,160,30);
        LB1.setBounds(220,100,100,30);

    }

}

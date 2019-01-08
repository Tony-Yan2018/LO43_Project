package userInterface;

import com.company.Controller;
import com.company.Hexagone;
import com.company.Maps;
import com.company.myGUIWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UseCardFrame extends JFrame {
    private int playerID;
    public UseCardFrame(int pID){
        super("Use Card");
        playerID=pID;
        JPanel useCard = new JPanel();
        setContentPane(useCard);
        setVisible(true);
        setLayout(null);
        setSize(600,500);
        useCard.setLayout(null);
        JButton bt1 = new JButton("Harvest Card: " + Controller.players[playerID].harvestCard);
        JButton bt2 = new JButton("Road Card: " + Controller.players[playerID].roadCard);
        JButton bt3 = new JButton("Score Card: " + Controller.players[playerID].currentScoreCard());
        JButton bt4 = new JButton("Get back to 1985!!!");
        JLabel LB1 = new JLabel("Use your cards or your time machine");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.players[playerID].harvestCard==0)
                    JOptionPane.showMessageDialog(null,"No more Harvest Card");
                else {
                    Controller.players[playerID].useCard(1);
                    setName("Harvest Card: "+Controller.players[playerID].harvestCard);
                }
                myGUIWindow.updateJTables();
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
                myGUIWindow.updateJTables();
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.players[playerID].scoreCardCount==0)
                    JOptionPane.showMessageDialog(null,"No more Score Card");
                else {
                    Controller.players[playerID].useCard(3);
                    setName("Score Card: "+Controller.players[playerID].scoreCardCount);
                }
                myGUIWindow.updateJTables();
            }
        });
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.players[Controller.flag].timeMachine && Controller.mapID!=4){
                    Controller.players[Controller.flag].useTimeMachine();
                    Controller.mapID=4;
                    myGUIWindow.dataTransfer();
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

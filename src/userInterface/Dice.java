package userInterface;
import com.company.Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Dice {
        public JLabel l1;
        public JLabel l2;
        public JLabel l3;
        public JLabel l4;
        public JLabel l5;
        public JButton b;
        public static boolean DiceClick=true;
	public Dice() {
        l1=new JLabel("Your counts are:");
        l2=new JLabel("");
        l3=new JLabel("");
        l4=new JLabel("Sum is : ");
        l5=new JLabel("");
        b=new JButton("Shake the dice");
        
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(DiceClick) {
	                Random random = new Random();
	            	int s1 = random.nextInt(6)%(6-1+1) + 1;
	            	int s2 = random.nextInt(6)%(6-1+1) + 1;
	            	Controller.currentDiceNumber=s1+s2;
	                l2.setText(String.valueOf(s1));
	                l3.setText(String.valueOf(s2));
	                l5.setText(String.valueOf(s1+s2));
	                Controller.addResource(s1+s2);
	//              System.out.println(Controller.players[1].resource[0]);
	//              Controller.players[1].resource[2]=10;
	                DiceClick=false;
            	}

            }
        });


	}

}

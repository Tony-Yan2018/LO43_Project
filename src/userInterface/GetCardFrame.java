package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.company.Controller;

public class GetCardFrame extends JFrame {
    JPanel JP;
    int thisPlayer;
    GetCardFrame(int idPlayer){
        super("Get Card");
        thisPlayer=idPlayer;
        setLayout(null);
        setSize(600,500);
        JP=new JPanel();setContentPane(JP);
        JP.setLayout(null);
        final JButton btn1 = new JButton("Confirm");btn1.setBounds(200,330,100,50);
        final JButton btn2 = new JButton("Cancel");btn2.setBounds(300,330,100,50);
	  /*  final JButton btn4 = new JButton("Choose Ressource");btn4.setBounds(130,100,100,50);
        final JButton btn5 = new JButton("Choose Ressource");btn5.setBounds(130,150,100,50);
        final JButton btn6 = new JButton("Choose Ressource");btn6.setBounds(130,200,100,50);
        final JButton btn7 = new JButton("Choose Ressource");btn7.setBounds(130,250,100,50);*/
        JLabel LB1 = new JLabel("Mineral (5 points)");LB1.setBounds(130,100,150,50);
        JLabel LB2 = new JLabel("Food (1 points)");LB2.setBounds(130,150,150,50);
        JLabel LB3 = new JLabel("Textile (2 points)");LB3.setBounds(130,200,150,50);
        JLabel LB4 = new JLabel("Material (2 points)");LB4.setBounds(130,250,150,50);
        /*final JButton btn8 = new JButton("Quantity");btn8.setBounds(350,100,100,50);
        final JButton btn9 = new JButton("Quantity");btn9.setBounds(350,150,100,50);
        final JButton btn10 = new JButton("Quantity");btn10.setBounds(350,200,100,50);
        final JButton btn11 = new JButton("Quantity");btn11.setBounds(350,250,100,50);*/
        final JTextField TX1 = new JTextField(30);TX1.setBounds(350,110,100,30);
        final JTextField TX2= new JTextField(30);TX2.setBounds(350,160,100,30);
        final JTextField TX3 = new JTextField(30);TX3.setBounds(350,210,100,30);
        final JTextField TX4 = new JTextField(30);TX4.setBounds(350,260,100,30);
        JP.add(btn1);JP.add(btn2);JP.add(LB1);JP.add(LB2);JP.add(LB3);
        JP.add(LB4);JP.add(TX1);JP.add(TX2);JP.add(TX3);JP.add(TX4);
        btn1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int Mineral =Integer.parseInt(TX1.getText().trim());
                int Food =Integer.parseInt(TX2.getText().trim());
                int Textile =Integer.parseInt(TX3.getText().trim());
                int Material =Integer.parseInt(TX4.getText().trim());
                int sum = Mineral*5+Food*1+Textile*2+Material*2;
                if(sum>=8) {
                    Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
                    int tmp=Controller.players[thisPlayer].getCard();
                    switch(tmp) {
                        case(1):
                            JOptionPane.showMessageDialog(null, "You have a Harvest Card !");
                            break;
                        case(2):
                            JOptionPane.showMessageDialog(null, "You have a Road Card !");
                            break;
                        case(3):
                            JOptionPane.showMessageDialog(null, "You have a Score Card !");
                            break;
                    }
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "You can't get a card !");
            }
        });

        btn2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }
	/*void res2Res(Object res,int Res) {
		if(res=="Mineral") {
			Res=0;
		}else if(res=="Food") {
			Res=1;
		}else if(res=="Textile") {
			Res=2;
		}else if(res=="Material") {
			Res=3;
		}
	}*/

}
package userInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import inGame.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import com.company.*;
public class ConstructFrame extends JFrame{
	JPanel JP;
	int thisPlayer;
	Object typeConstruct;
	Object res1,res2,res3,res4;
	int Res1,Res2,Res3,Res4;
	String qtty1,qtty2,qtty3,qtty4;
	int q1,q2,q3,q4;
	static final JButton btn1 = new JButton("Confirm");
	static final JButton btn2 = new JButton("Cancel");
	static JComboBox CB1 = new JComboBox();
	static JLabel LB1 = new JLabel("Mineral (5 points)");
	static JLabel LB2 = new JLabel("Food (1 points)");
	static JLabel LB3 = new JLabel("Textile (2 points)");
	static JLabel LB4 = new JLabel("Material (2 points)");
	static final JLabel LB11 = new JLabel("Quantity:");
	static final JLabel LB21 = new JLabel("Quantity:");
	static final JLabel LB31 = new JLabel("Quantity:");
	static final JLabel LB41 = new JLabel("Quantity:");
	static final JTextField TX1 = new JTextField(30);
	static final JTextField TX2= new JTextField(30);
	static final JTextField TX3 = new JTextField(30);
	static final JTextField TX4 = new JTextField(30);
	ConstructFrame(int idPlayer){
		super("Construction");
		thisPlayer=idPlayer;
		setLayout(null);
		setSize(300,300);
		JP=new JPanel();
		setContentPane(JP);
		setResizable(false);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension Dt = t.getScreenSize();
		setLocation((int)(Dt.getWidth()*0.77),(int)(Dt.getHeight()*0.3));
		setVisible(true);
		JP.setLayout(null);
		if(Controller.mapID==4)
			CB1 = new JComboBox(new String[]{"Village", "City","Road"});
		else
			CB1 = new JComboBox(new String[]{"Village", "City","Road","Time Machine"});

		btn1.setBounds(20,180,80,30); JP.add(btn1);
		btn2.setBounds(130,180,80,30); JP.add(btn2);
		CB1.setBounds(10,0,100,25); JP.add(CB1);
		LB1.setBounds(10,20,120,50); JP.add(LB1);
		LB2.setBounds(10,45,120,50); JP.add(LB2);
		LB3.setBounds(10,70,120,50); JP.add(LB3);
		LB4.setBounds(10,95,120,50); JP.add(LB4);
		LB11.setBounds(130,20,100,50); JP.add(LB11);
		LB21.setBounds(130,45,100,50); JP.add(LB21);
		LB31.setBounds(130,70,100,50); JP.add(LB31);
		LB41.setBounds(130,95,100,50); JP.add(LB41);
		TX1.setBounds(200,40,60,20); JP.add(TX1);
		TX2.setBounds(200,65,60,20); JP.add(TX2);
		TX3.setBounds(200,90,60,20); JP.add(TX3);
		TX4.setBounds(200,115,60,20); JP.add(TX4);


		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int Mineral =Integer.parseInt(TX1.getText().trim());
				int Food =Integer.parseInt(TX2.getText().trim());
				int Textile =Integer.parseInt(TX3.getText().trim());
				int Material =Integer.parseInt(TX4.getText().trim());
				if(Mineral>Controller.players[Controller.flag].resource[0]||Food>Controller.players[Controller.flag].resource[1]||Textile>Controller.players[Controller.flag].resource[2]||Material>Controller.players[Controller.flag].resource[3]){
					JOptionPane.showMessageDialog(null,"Your resource is not enough!");
				}
				else{
					int sum = Mineral*5+Food*1+Textile*2+Material*2;
					String selection = (String)CB1.getSelectedItem();
					if(sum>=5) {
						switch (selection){
							case ("Village"):
								if(sum>=7) {
									JOptionPane.showMessageDialog(null, "You can build a village!");
									Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
//								Controller.players[thisPlayer].score++;
									Controller.act = 0;
									dispose();
								}
								break;
							case("City"):
								if(sum>=9){
									JOptionPane.showMessageDialog(null, "You can build a city!");
									Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
//								Controller.players[thisPlayer].score+=2;
									Controller.act=1;
									dispose();
								}
								break;
							case("Road"):
								if(sum>=5){
									JOptionPane.showMessageDialog(null, "You can build a road!");
									Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
									Controller.act=2;
									int leftPoint = 0;
									int rightPoint = 0;
									String str = JOptionPane.showInputDialog("Input two numbers separated by ';'");
									if(!"".equals(str)){
										String[] strs = str.split(";");
										if(strs.length==2) {
											if (Integer.parseInt(strs[0]) > Integer.parseInt(strs[1])) {
												leftPoint = Integer.parseInt(strs[1]);
												rightPoint = Integer.parseInt(strs[0]);
											} else {
												leftPoint = Integer.parseInt(strs[0]);
												rightPoint = Integer.parseInt(strs[1]);
											}
											Controller.ALR.addRoad(leftPoint,rightPoint,Controller.flag);
										}
										else {
											JOptionPane.showMessageDialog(null,"Please enter 2 numbers!");
										}
									}
									else
										JOptionPane.showMessageDialog(null,"Please enter 2 numbers!");
								}
							case ("Time Machine"):
								if(sum>=10){
									Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
									Controller.players[Controller.flag].timeMachine=true;
									JOptionPane.showMessageDialog(null,"You've got a time machine");
								}
						}
					}
					else
						JOptionPane.showMessageDialog(null, "You can't build");
					myGUIWindow.updateJTables();
				}
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});

	}
}

package userInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import inGame.*;
import javax.swing.*;
import com.company.*;
public class TradeFrame extends JFrame{
	private int tradePlayer,thisPlayer;
	private int[] idPlayers= new int[3];

	TradeFrame(String title,int idPlayer){
		super(title);
		thisPlayer=idPlayer;
		switch(Controller.flag) {
			case(0):
				idPlayers[0]=2;
				idPlayers[1]=3;
				idPlayers[2]=4;
				break;
			case(1):
				idPlayers[0]=1;
				idPlayers[1]=3;
				idPlayers[2]=4;
				break;
			case(3):
				idPlayers[0]=1;
				idPlayers[1]=2;
				idPlayers[2]=3;
				break;
		}
		setLayout(null);
		setSize(300,300);
		JPanel JP = new JPanel();
		setContentPane(JP);
		setResizable(false);
		setVisible(true);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension Dt = t.getScreenSize();
		setLocation((int)(Dt.getWidth()*0.77),(int)(Dt.getHeight()*0.3));
		JP.setLayout(null);
		final JButton btn1 = new JButton("Confirm");
		btn1.setBounds(10,200,80,30);
		JP.add(btn1);
		final JButton btn2 = new JButton("Cancel");
		btn2.setBounds(110,200,80,30);
		JP.add(btn2);
		final JComboBox CB1 = new JComboBox<>(new String[]{"Mineral", "Food", "Textile","Material"});
		CB1.setBounds(10, 30, 100, 20);
		JP.add(CB1);
		final JTextField TX1 = new JTextField(30);
		TX1.setBounds(120, 30, 100, 30);
		JP.add(TX1);
		final JComboBox CB2 = new JComboBox<>(new String[]{"Mineral", "Food", "Textile","Material"});
		CB2.setBounds(10, 140, 100, 20);
		JP.add(CB2);
		final JTextField TX2 = new JTextField(30);
		TX2.setBounds(120, 140, 100, 30);
		JP.add(TX2);
		final JLabel label5 = new JLabel("Player to trade");
		label5.setBounds(10,90,100,20);
		JP.add(label5);
		final ButtonGroup group= new ButtonGroup();
		final JRadioButton s1=new JRadioButton(String.valueOf(idPlayers[0]));group.add(s1);
		JP.add(s1);
		final JRadioButton s2=new JRadioButton(String.valueOf(idPlayers[1]));group.add(s2);
		JP.add(s2);
		final JRadioButton s3=new JRadioButton(String.valueOf(idPlayers[2]));group.add(s3);
		JP.add(s3);
		s1.setBounds(115,90,40,20);
		s2.setBounds(155,90,40,20);
		s3.setBounds(195,90,40,20);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Resname1 = (String)CB1.getSelectedItem();
				String Resname2 = (String)CB2.getSelectedItem();
				int s1 =Integer.parseInt(TX1.getText().trim());
				int s2 =Integer.parseInt(TX2.getText().trim());
				switch(Resname1) {
					case("Mineral"):
						Controller.players[thisPlayer].resource[0]-=s1;
						Controller.players[tradePlayer].resource[0]+=s1;
						break;
					case("Food"):
						Controller.players[thisPlayer].resource[1]-=s1;
						Controller.players[tradePlayer].resource[1]+=s1;
						break;
					case("Textile"):
						Controller.players[thisPlayer].resource[2]-=s1;
						Controller.players[tradePlayer].resource[2]+=s1;
						break;
					case("Material"):
						Controller.players[thisPlayer].resource[3]-=s1;
						Controller.players[tradePlayer].resource[3]+=s1;
						break;
				}
				switch(Resname2) {
					case("Mineral"):
						Controller.players[thisPlayer].resource[0]+=s2;
						Controller.players[tradePlayer].resource[0]-=s2;
						break;
					case("Food"):
						Controller.players[thisPlayer].resource[1]+=s2;
						Controller.players[tradePlayer].resource[1]-=s2;
						break;
					case("Textile"):
						Controller.players[thisPlayer].resource[2]+=s2;
						Controller.players[tradePlayer].resource[2]-=s2;
						break;
					case("Material"):
						Controller.players[thisPlayer].resource[3]+=s2;
						Controller.players[tradePlayer].resource[3]-=s2;
						break;
				}
				myGUIWindow.updateJTables();
			}
		});
		s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tradePlayer=idPlayers[0]-1;
			}
		});
		s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tradePlayer=idPlayers[1]-1;
			}
		});
		s3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tradePlayer=idPlayers[2]-1;
			}
		});
	}}
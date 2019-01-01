package userInterface;
import java.awt.Component;
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
    static final JComboBox CB1 = new JComboBox(new String[]{"Village", "City","Road"});
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
		setSize(600,500);
		JP=new JPanel();
		setContentPane(JP);
		JP.setLayout(null);
        btn1.setBounds(200,330,100,50); JP.add(btn1);
        btn2.setBounds(300,330,100,50); JP.add(btn2);
        CB1.setBounds(50,30,100,25); JP.add(CB1);
        LB1.setBounds(130,100,120,50); JP.add(LB1);
        LB2.setBounds(130,150,120,50); JP.add(LB2);
        LB3.setBounds(130,200,120,50); JP.add(LB3);
        LB4.setBounds(130,250,120,50); JP.add(LB4);
        LB11.setBounds(350,100,100,50); JP.add(LB11);
        LB21.setBounds(350,150,100,50); JP.add(LB21);
        LB31.setBounds(350,200,100,50); JP.add(LB31);
        LB41.setBounds(350,250,100,50); JP.add(LB41);
        TX1.setBounds(410,110,60,30); JP.add(TX1);
		TX2.setBounds(410,160,60,30); JP.add(TX2);
		TX3.setBounds(410,210,60,30); JP.add(TX3);
		TX4.setBounds(410,260,60,30); JP.add(TX4);


        btn1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e){
        		int Mineral =Integer.parseInt(TX1.getText().trim());
				int Food =Integer.parseInt(TX2.getText().trim());
				int Textile =Integer.parseInt(TX3.getText().trim());
				int Material =Integer.parseInt(TX4.getText().trim());
				int sum = Mineral*5+Food*1+Textile*2+Material*2;
				String selection = (String)CB1.getSelectedItem();

				switch (selection){
					case ("Village"):
						if(sum>=7) {
                            JOptionPane.showMessageDialog(null, "You can build a village!");
                            Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
                            Controller.act = 0;
                        }
						break;
					case("City"):
						if(sum>=9){
							JOptionPane.showMessageDialog(null, "You can build a city!");
							Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
							Controller.act=1;
						}
						break;
					case("Road"):
						if(sum>=5){
							JOptionPane.showMessageDialog(null, "You can build a city!");
							Controller.players[thisPlayer].useRes(Mineral, Food, Textile, Material);
							Controller.act=2;
						}

				}


//        		q1=Integer.parseInt(qtty1);q2=Integer.parseInt(qtty2);
//        		q3=Integer.parseInt(qtty3);q4=Integer.parseInt(qtty4);
//        		res2Res(res1,Res1);res2Res(res2,Res2);
//				res2Res(res3,Res3);res2Res(res4,Res4);
//        		Controller.PM.PS[thisPlayer].Construct(typeConstruct,Res1,Res2,Res3,Res4,q1,q2,q3,q4);
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
	void res2Res(Object res,int Res) {
		if(res=="Mineral") {
			Res=0;
		}else if(res=="Food") {
			Res=1;
		}else if(res=="Textile") {
			Res=2;
		}else if(res=="Material") {
			Res=3;
		}
	}
    
}

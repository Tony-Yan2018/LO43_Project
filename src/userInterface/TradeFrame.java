package userInterface;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import inGame.*;
import javax.swing.*;
import com.company.*;
public class TradeFrame extends JFrame{
	Object res1;
	Object res2;
	int Res1,Res2;
	String qtty1,qtty2;
	int Quantity1,Quantity2;
	int tradePlayer,thisPlayer;
	int[] idPlayers=new int[3];
	JPanel JP;
	TradeFrame(String title,int idPlayer){
		super(title);
		thisPlayer=idPlayer-1;
		setLayout(null);
		setSize(600,500);
		setIdPlayers(idPlayer);
		JP=new JPanel();
		setContentPane(JP);
		JP.setLayout(null);
		final JButton btn1 = new JButton("Confirm");btn1.setBounds(200,330,100,50);JP.add(btn1);
        final JButton btn2 = new JButton("Cancel");btn2.setBounds(300,330,100,50);JP.add(btn2);
        final JButton btn3 = new JButton("Quantity");btn3.setBounds(350,80,100,50);JP.add(btn3);
        final JButton btn4 = new JButton("Quantity");btn4.setBounds(350,200,100,50);JP.add(btn4);
        final JButton btn5 = new JButton("typeRes");btn5.setBounds(150,80,100,50);JP.add(btn5);
        final JButton btn6 = new JButton("typeRes");btn6.setBounds(150,200,100,50);JP.add(btn6);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] selectionValues = new Object[]{"Mineral", "Food", "Textile","Material"};
                res1 = JOptionPane.showInputDialog(
                		JP,
                        "Type of Ressource",
                        "choose dialogue",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectionValues,
                        selectionValues[0]
                );
                //System.out.println("res" + inputContent);
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] selectionValues = new Object[]{"Mineral", "Food", "Textile","Material"};
                res2 = JOptionPane.showInputDialog(
                		JP,
                        "Type of Ressource",
                        "choose dialogue",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectionValues,
                        selectionValues[0]
                );
                //System.out.println("res" + inputContent);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                qtty1 = JOptionPane.showInputDialog(
                        JP,
                        "Quantity:",
                        "choose quantity"
                );
                System.out.println("" + qtty1);
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                qtty2 = JOptionPane.showInputDialog(
                        JP,
                        "Quantity:",
                        "choose quantity"
                );
                //System.out.println("" + inputContent);
            }
        });
        btn2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
           {
        		dispose();
           }
        });
        btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Quantity1=Integer.parseInt(qtty1);
				System.out.println(Quantity1);
				Quantity2=Integer.parseInt(qtty2);
				res2Res(res1,Res1);
				res2Res(res2,Res2);
				Controller.PM.tradePlayers(Res1,Res2,Quantity1,Quantity2,thisPlayer,tradePlayer);
				for(int i=0;i<4;i++) {
				Controller.ListT.LT[i].repaint();
				Controller.Jscrolls[i].updateUI();}
			}
        });
        final JLabel label5 = new JLabel("Player to trade");
        label5.setBounds(40,140,100,50);
        JP.add(label5);
        final ButtonGroup group= new ButtonGroup();
        final JRadioButton s1=new JRadioButton(String.valueOf(idPlayers[0]));group.add(s1);JP.add(s1);
    	final JRadioButton s2=new JRadioButton(String.valueOf(idPlayers[1]));group.add(s2);JP.add(s2);
    	final JRadioButton s3=new JRadioButton(String.valueOf(idPlayers[2]));group.add(s3);JP.add(s3);
    	s1.setBounds(150,140,100,50);s2.setBounds(250,140,100,50);s3.setBounds(350,140,100,50);
    	s1.setVisible(true);s2.setVisible(true);s3.setVisible(true);
    	s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tradePlayer=idPlayers[0];
			}
    	});
    	s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tradePlayer=idPlayers[1];
			}
    	});
    	s3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tradePlayer=idPlayers[2];
			}
    	});
    	setLocationRelativeTo(null);
        setVisible(true);
        
	}
	void setIdPlayers(int i) {
		switch (i) {
		case 1:
			idPlayers[0]=1;
			idPlayers[1]=2;
			idPlayers[2]=3;
			break;
		case 2:
			idPlayers[0]=0;
			idPlayers[1]=2;
			idPlayers[2]=3;
			break;
		case 3:
			idPlayers[0]=0;
			idPlayers[1]=1;
			idPlayers[2]=3;
			break;
		case 4:
			idPlayers[0]=0;
			idPlayers[1]=1;
			idPlayers[2]=2;
			break;
		}
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
//	static public void main(String[] args) {
//		TradeFrame TF=new TradeFrame("Test", 1);
//	}
}

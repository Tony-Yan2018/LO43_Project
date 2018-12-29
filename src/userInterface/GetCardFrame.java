package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.company.Controller;

public class GetCardFrame extends JFrame {
	JPanel JP;
	int thisPlayer;
	
	Object res1,res2,res3,res4;
	int Res1,Res2,Res3,Res4;
	String qtty1,qtty2,qtty3,qtty4;
	int q1,q2,q3,q4;
	GetCardFrame(String title,int idPlayer){
		super(title);
		thisPlayer=idPlayer;
		setLayout(null);
		setSize(600,500);
		JP=new JPanel();setContentPane(JP);
		JP.setLayout(null);
		final JButton btn1 = new JButton("Confirm");btn1.setBounds(200,330,100,50);
	    final JButton btn2 = new JButton("Cancel");btn2.setBounds(300,330,100,50);
	    final JButton btn4 = new JButton("Choose Ressource");btn4.setBounds(130,100,100,50);
        final JButton btn5 = new JButton("Choose Ressource");btn5.setBounds(130,150,100,50);
        final JButton btn6 = new JButton("Choose Ressource");btn6.setBounds(130,200,100,50);
        final JButton btn7 = new JButton("Choose Ressource");btn7.setBounds(130,250,100,50);
        final JButton btn8 = new JButton("Quantity");btn8.setBounds(350,100,100,50);
        final JButton btn9 = new JButton("Quantity");btn9.setBounds(350,150,100,50);
        final JButton btn10 = new JButton("Quantity");btn10.setBounds(350,200,100,50);
        final JButton btn11 = new JButton("Quantity");btn11.setBounds(350,250,100,50);
        JP.add(btn1);JP.add(btn2);JP.add(btn4);JP.add(btn5);JP.add(btn6);
        JP.add(btn7);JP.add(btn8);JP.add(btn9);JP.add(btn10);JP.add(btn11);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] selectionValues = new Object[]{"Mineral", "Food", "Textile","Material"};             
                res1 = JOptionPane.showInputDialog(
                        JP,
                        "Choose your ressource: ",
                        "Ressource",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectionValues,
                        selectionValues[0]
                );
                //System.out.println("输入的内容: " + inputContent);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] selectionValues = new Object[]{"Mineral", "Food", "Textile","Material"};             
                res2 = JOptionPane.showInputDialog(
                        JP,
                        "Choose your ressource: ",
                        "Ressource",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectionValues,
                        selectionValues[0]
                );
                //System.out.println("输入的内容: " + inputContent);
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] selectionValues = new Object[]{"Mineral", "Food", "Textile","Material"};             
                res3 = JOptionPane.showInputDialog(
                        JP,
                        "Choose your ressource: ",
                        "Ressource",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectionValues,
                        selectionValues[0]
                );
                //System.out.println("输入的内容: " + inputContent);
            }
        });        
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] selectionValues = new Object[]{"Mineral", "Food", "Textile","Material"};             
                res4 = JOptionPane.showInputDialog(
                        JP,
                        "Choose your ressource: ",
                        "Ressource",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectionValues,
                        selectionValues[0]
                );
                //System.out.println("输入的内容: " + inputContent);
            }
        });
        btn8.addActionListener(new ActionListener() {
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
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                qtty2 = JOptionPane.showInputDialog(
                        JP,
                        "Quantity:",
                        "choose quantity"
                );
                System.out.println("" + qtty1);
            }
        });
        btn10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                qtty3 = JOptionPane.showInputDialog(
                        JP,
                        "Quantity:",
                        "choose quantity"
                );
                System.out.println("" + qtty1);
            }
        });
        btn11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                qtty4 = JOptionPane.showInputDialog(
                        JP,
                        "Quantity:",
                        "choose quantity"
                );
                System.out.println("" + qtty1);
            }
        });
        btn1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
           {
        		q1=Integer.parseInt(qtty1);q2=Integer.parseInt(qtty2);
        		q3=Integer.parseInt(qtty3);q4=Integer.parseInt(qtty4);
        		res2Res(res1,Res1);res2Res(res2,Res2);
				res2Res(res3,Res3);res2Res(res4,Res4);
				Controller.PM.PS[thisPlayer].getCard(Res1,Res2,Res3,Res4,q1,q2,q3,q4);
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

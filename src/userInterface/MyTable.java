package userInterface;

import javax.swing.*;
import javax.swing.plaf.TableUI;
import javax.swing.table.JTableHeader;

public class MyTable extends JTable{
	public MyTable(Object[][] rowData, Object[] columnNames){
		super(rowData,columnNames);
		setEnabled(false);// table not editable
		JTableHeader jTableHeader = this.getTableHeader();
		jTableHeader.setResizingAllowed(false);// resizing of columns not allowed
		jTableHeader.setReorderingAllowed(false);//reordering of columns not allowed
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				for(int i=0;i<4;i++) {
//					LT[i].updateUI();
//				}
				updateUI();
//                    try {
//                        Thread.sleep(1000);
//                    }
//                    catch(InterruptedException e){
//                        e.printStackTrace();
//                    }
			}
		});
	}
	public void updateUI() {
		setUI((TableUI)UIManager.getUI(this));
	}
}

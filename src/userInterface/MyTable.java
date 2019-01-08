package userInterface;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.TableUI;
import javax.swing.table.JTableHeader;

public class MyTable extends JTable{
	public MyTable(Object[][] rowData, Object[] columnNames){
		super(rowData,columnNames);
		setEnabled(false);// table not editable
		JTableHeader jTableHeader = this.getTableHeader();
		jTableHeader.setResizingAllowed(false);// resizing of columns not allowed
		jTableHeader.setReorderingAllowed(false);//reordering of columns not allowed
		dataModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE){
					if (e.getColumn()==1) {
						for(int i=0;i<7;i++){
							setValueAt(dataModel.getValueAt(i,1),i,1);
						}
					}
				}
				repaint();
			}
		});
	}
	public void updateUI() {
		setUI((TableUI)UIManager.getUI(this));
	}
}

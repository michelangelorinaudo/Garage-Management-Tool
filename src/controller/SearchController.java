package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Database;

/**
 * Class that has the role of allowing the search functionality.
 * @author MR
 *
 */
public class SearchController implements KeyListener
{

	@Override
	public void keyReleased(KeyEvent e)
	{
		DefaultTableModel model = (DefaultTableModel)Database.getInstance().getModel();
		String search = Database.getInstance().getFilterTextField().getText().toUpperCase();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		Database.getInstance().getTable().setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));	
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

}

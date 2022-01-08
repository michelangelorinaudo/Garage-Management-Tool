package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Database;

/**
 * Class that has the role of clearing the JTable of all the entries.
 * @author MR
 */
public class ClearTableController implements ActionListener
{

	private Database database;
	
	/**
	 * Class constructor.
	 * @param database
	 */
	public ClearTableController(Database database)
	{
		super();
		this.database = database;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		while (database.getTable().getRowCount() > 0)
			database.getModel().removeRow(0);
		database.getTotalField().setText("0");
		JOptionPane.showMessageDialog(null, "Todos los clientes han sido eliminados");	
	}

}

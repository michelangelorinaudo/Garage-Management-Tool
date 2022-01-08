package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.Database;

/**
 * Class that has the role of deleting a database entry(customer) from the JTable.
 * @author MR
 *
 */
public class DeleteController implements ActionListener
{

	private Database database;
	
	/**
	 * Class constructor.
	 * @param database
	 */
	public DeleteController(Database database)
	{
		super();
		this.database = database;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{        
        int[] selectedRows = database.getTable().getSelectedRows();
     
        if (selectedRows.length > 0)
        {
        	for(int i = 0; i < selectedRows.length; i++)
        	{
        		database.getModel().removeRow(selectedRows[i]-i);
        	}
            JOptionPane.showMessageDialog(null, "Cliente/s eliminado/s");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccionar cliente");
        }
	}

}

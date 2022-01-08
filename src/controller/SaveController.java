package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.Database;
import view.GarageWindow;

/**
 * Class that has the role of saving the customer data inserted into the JTable.
 * @author MR
 *
 */
public class SaveController implements ActionListener
{

	private GarageWindow theView;
	
	/**
	 * Class constructor.
	 * @param theView
	 */
	public SaveController(GarageWindow theView)
	{
		super();
		this.theView = theView;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Database.getInstance().getModel().addRow(new Object[]{theView.getFirstName().getText().toUpperCase(),
				theView.getSurname().getText().toUpperCase(), theView.getVehicle().getText().toUpperCase(),
				theView.getLicensePlate().getText().toUpperCase(), theView.getPhoneNumber().getText().toUpperCase(),
				theView.getPrice().getText().toUpperCase()});

		JOptionPane.showMessageDialog(null, "        Cliente adicionado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		new ClearController(theView).actionPerformed(e);
	}

}

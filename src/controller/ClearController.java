package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GarageWindow;

/**
 * Class that has the role of clearing the jtextfields of the main window (view).
 * @author MR
 */
public class ClearController implements ActionListener
{

	private GarageWindow theView;
	
	/**
	 * Class constructor.
	 * @param theView
	 */
	public ClearController(GarageWindow theView)
	{
		this.theView = theView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		theView.getFirstName().setText("");
		theView.getSurname().setText("");
		theView.getVehicle().setText("");
		theView.getLicensePlate().setText("");
		theView.getPhoneNumber().setText("");
		theView.getPrice().setText("");
	}

}

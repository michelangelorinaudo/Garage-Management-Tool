package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Database;

/**
 * Class that has the role of making the database window visible to the user.
 * @author MR
 *
 */
public class DatabaseController implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Database.getInstance().setVisible(true);
	}
}

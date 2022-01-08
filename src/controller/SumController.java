package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Database;

/**
 * Class that has the role of getting the sum of values on th prices column.
 * @author MR
 */
public class SumController implements ActionListener
{
	private Database database;

	/**
	 * Class constructor.
	 * @param database
	 */
	public SumController(Database database)
	{
		super();
		this.database = database;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		ArrayList<String> prices = new ArrayList<String>();

		int numberOfRows = database.getTable().getRowCount();
		int numberOfColumns = database.getTable().getColumnCount();
		
        if (numberOfRows > 0)
        {
        	double sum = 0.0;
        	
        	for(int i = 0; i < numberOfRows; i++)
        	{
        		prices.add((String) database.getTable().getValueAt(i, numberOfColumns - 1));
        	}
        	
        	for (String s : prices)
        	{
        		String newString = s;
        		
        		if (s.contains(","))
        		{
        			newString = newString.replace(',', '.');
        		}
        		
        		try
        		{
        			sum += Double.parseDouble(newString);
        		}
        		catch(NumberFormatException ex){}
        	}
        	
        	database.getTotalField().setText(Double.toString(sum));
        }	
	}
}

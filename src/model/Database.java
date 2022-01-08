package model;

import java.awt.Dimension;

import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import controller.ClearTableController;
import controller.DeleteController;
import controller.ExportController;
import controller.SearchController;
import controller.SumController;
import controller.WindowController;

/**
 * Class that has the role of setting the JTable window and storing the customer data.
 * It uses the Singleton Design Pattern.
 * @author MR
 *
 */
public class Database extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private TableRowSorter<TableModel> rowSorter;
	
	private JTextField searchField = new JTextField();
	private JLabel searchLabel;
	private JButton clearButton;
	private JButton deleteButton;
	private JButton exportButton;
	
	private JTextField totalField = new JTextField();
	private JButton totalButton;
	
	/**
	 * It allows the instanciation of the class.
	 */
	public static Database instance;
	
	/**
	 * Method that limits this class to a single instance.
	 * @return
	 */
	public static Database getInstance()
	{
		if (instance == null)
			instance = new Database();
		return instance;
	}
	
	private Database()
	{
		super("Lista De Clientes");
		WindowController.lookAndFeelSetter();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		rowSorter = new TableRowSorter<>(model);
		searchField = new JTextField(20);
		totalField = new JTextField("0", 5);
		
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel deleteButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel clearButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel exportButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel totalButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
 
        searchLabel = new JLabel("Buscar");
		clearButton = new JButton("Limpiar");
		deleteButton = new JButton("Eliminar");
		exportButton = new JButton("Exportar");
		totalButton = new JButton("Suma Precios €: ");
		
		searchButtonPanel.add(searchLabel);
		searchButtonPanel.add(searchField);
		deleteButtonPanel.add(deleteButton);
		clearButtonPanel.add(clearButton);
		exportButtonPanel.add(exportButton);
		totalButtonPanel.add(totalButton);
		totalButtonPanel.add(totalField);
		
		
		buttonPanel.add(searchButtonPanel, BorderLayout.CENTER);
		buttonPanel.add(deleteButtonPanel, BorderLayout.EAST);
		buttonPanel.add(clearButtonPanel, BorderLayout.SOUTH);
		buttonPanel.add(exportButtonPanel, BorderLayout.WEST);
		buttonPanel.add(totalButtonPanel, BorderLayout.NORTH);
			
		
		String[] columns = {"Nombre", "Apellido", "Veiculo", "Targa", "Telefono", "Precio €"};
		Object[][] data = {};
		
		model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		table.setRowSorter(rowSorter);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		this.add(table.getTableHeader(), BorderLayout.NORTH);
	    this.add(table, BorderLayout.CENTER);
	    this.add(buttonPanel, BorderLayout.SOUTH);
	    this.add(new JScrollPane(table), BorderLayout.CENTER);
	    
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		searchField.addKeyListener(new SearchController());
		clearButton.addActionListener(new ClearTableController(this));
		deleteButton.addActionListener(new DeleteController(this));
		exportButton.addActionListener(new ExportController(this));
		totalButton.addActionListener(new SumController(this));
	
	}
	
	/**
	 * Method that has the role of creating a file.
	 * @param file
	 */
	public void createFile(String file)
	{
		try
		{
			File path = new File(file);
			Desktop.getDesktop().open(path);
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
		}
	}

	/**
	 * Method that gets the default table model.
	 * @return
	 */
	public DefaultTableModel getModel()
	{
		return model;
	}

	/**
	 * Method that gets the current JTable.
	 * @return
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * Method that gets the data inside the search field.
	 * @return
	 */
	public JTextField getFilterTextField()
	{
		return searchField;
	}

	/**
	 * Method that gets the data inside the total field.
	 * @return
	 */
	public JTextField getTotalField()
	{
		return totalField;
	}
}

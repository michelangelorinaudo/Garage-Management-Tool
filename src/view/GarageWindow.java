package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.ClearController;
import controller.DatabaseController;
import controller.SaveController;
import controller.WindowController;
import model.Database;


/**
 * This class contains the information representing all fields and buttons
 * of the main window. It uses the Singleton Design Pattern.
 * @author MR
 *
 */
public class GarageWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel panel; 
	private JButton saveButton;
	private JButton clearButton;
	private JButton databaseButton;
	
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel vehicleLabel;
	private JLabel licensePlateLabel;
	private JLabel phoneNumberLabel;
	private JLabel priceLabel;
	private JTextField name;
	private JTextField surname;
	private JTextField vehicle;
	private JTextField licensePlate;
	private JTextField phoneNumber;
	private JTextField price;

	/**
	 * It allows the instanciation of the class.
	 */
	public static GarageWindow instance;
	
	/**
	 * It allows the creation of a instance of type GarageWindow, restricting it to
	 * a single object.
	 * @return instance
	 */
	public static GarageWindow getInstance()
	{

		if (instance == null)
			instance = new GarageWindow();
		return instance;
	}
	
	private GarageWindow()
	{
		super("Registro Clientes v.1.1");
		WindowController.lookAndFeelSetter();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 835, 250);
		
		panel = new JPanel();
		panel.setLayout(null);
	
		nameLabel = new JLabel("Nombre");
		name = new JTextField();
		
		surnameLabel = new JLabel("Apellido");
		surname = new JTextField();
		
		vehicleLabel = new JLabel("Veiculo");
		vehicle = new JTextField();
		
		licensePlateLabel = new JLabel("Targa");
		licensePlate = new JTextField();
		
		phoneNumberLabel = new JLabel("Telefono");
		phoneNumber = new JTextField();
		
		priceLabel = new JLabel("Precio €");
		price = new JTextField();
		
		saveButton = new JButton("Grabar");
		clearButton = new JButton("Limpiar");
		databaseButton = new JButton("Lista De Clientes");
		
		panel.add(nameLabel);
		panel.add(name);
		panel.add(surnameLabel);
		panel.add(surname);
		panel.add(vehicleLabel);
		panel.add(vehicle);
		panel.add(licensePlateLabel);
		panel.add(licensePlate);
		panel.add(phoneNumberLabel);
		panel.add(phoneNumber);
		panel.add(priceLabel);
		panel.add(price);
		panel.add(saveButton);
		panel.add(clearButton);
		panel.add(databaseButton);
		
		nameLabel.setBounds(35, 0, 100, 35);
		name.setBounds(10, 40, 100, 35);
		
		surnameLabel.setBounds(178, 0, 100, 35); // +30
		surname.setBounds(150, 40, 100, 35); // +140
		
		vehicleLabel.setBounds(320, 0, 100, 35); 
		vehicle.setBounds(290, 40, 100, 35); 
		
		licensePlateLabel.setBounds(460, 0, 100, 35);
		licensePlate.setBounds(430, 40, 100, 35);
		
		phoneNumberLabel.setBounds(593, 0, 100, 35);
		phoneNumber.setBounds(570, 40, 100, 35);
		
		priceLabel.setBounds(735, 0, 100, 35);
		price.setBounds(710, 40, 100, 35);
		
		saveButton.setBounds(225, 120, 100, 35);
		clearButton.setBounds(335, 120, 100, 35);
		databaseButton.setBounds(445, 120, 150, 35);
		
		saveButton.addActionListener(new SaveController(this));
		clearButton.addActionListener(new ClearController(this));
		databaseButton.addActionListener(new DatabaseController());
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(panel);
		this.addWindowListener(new WindowController(Database.getInstance(), this));
	}

	/**
	 * Method that gets the customer's first name.
	 * @return
	 */
	public JTextField getFirstName()
	{
		return name;
	}

	/**
	 * Method that gets the customer's last name.
	 * @return
	 */
	public JTextField getSurname()
	{
		return surname;
	}

	/**
	 * Method that gets the customer's vehicle.
	 * @return
	 */
	public JTextField getVehicle()
	{
		return vehicle;
	}

	/**
	 * Method that gets the customer's licence plate.
	 * @return
	 */
	public JTextField getLicensePlate()
	{
		return licensePlate;
	}

	/**
	 * Method that gets the customer's phone number.
	 * @return
	 */
	public JTextField getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Method that gets the customer's costs or the parking space price.
	 * @return
	 */
	public JTextField getPrice()
	{
		return price;
	}
}

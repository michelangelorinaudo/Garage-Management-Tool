package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Database;
import view.GarageWindow;

/**
 * Class that has the role of setting a close window prompt advising to save the data
 * onto an Excel file/spreadsheet.
 * @author MR
 *
 */
public class WindowController implements WindowListener
{

	private Database database;
	private GarageWindow theView;

	/**
	 * Class constructor.
	 * @param database
	 * @param theView
	 */
	public WindowController(Database database, GarageWindow theView)
	{
		super();
		this.database = database;
		this.theView = theView;
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
	    	int confirmed = JOptionPane.showOptionDialog(null, 
	            "Quieres guardar el archivo ?", 
	            "Cerrar Programa", 
	            JOptionPane.YES_NO_CANCEL_OPTION, 
	            JOptionPane.INFORMATION_MESSAGE, 
	            null, 
	            new String[]{"Sí", "No", "Cancelar"},
	            "default");

		    if (confirmed == JOptionPane.YES_OPTION)
		    {	
		    	try
				{
		    		UIManager.put("FileChooser.saveDialogTitleText", "Guardar");
					UIManager.put("FileChooser.openDialogTitleText", "Abrir");
					UIManager.put("FileChooser.lookInLabelText", "Guardar en");
					UIManager.put("FileChooser.saveInLabelText", "Guardar en");
					UIManager.put("FileChooser.openButtonText", "Abrir");
					UIManager.put("FileChooser.cancelButtonText", "Cancelar");
					UIManager.put("FileChooser.saveButtonText", "Guardar");
					UIManager.put("FileChooser.fileNameLabelText", "Nombre de archivo");
					UIManager.put("FileChooser.filesOfTypeLabelText", "Archivos de tipo");
					UIManager.put("FileChooser.openButtonToolTipText", "Abrir archivo seleccionado");
					UIManager.put("FileChooser.cancelButtonToolTipText","Cancelar");
					UIManager.put("FileChooser.fileNameHeaderText","Nombre de archivo");
					UIManager.put("FileChooser.upFolderToolTipText", "Un nivel arriba");
					UIManager.put("FileChooser.homeFolderToolTipText","Escritorio");
					UIManager.put("FileChooser.newFolderToolTipText","Nueva Carpeta");
					UIManager.put("FileChooser.listViewButtonToolTipText","Lista");
					UIManager.put("FileChooser.newFolderButtonText","Nueva Carpeta");
					UIManager.put("FileChooser.renameFileButtonText", "Renombrar archivo");
					UIManager.put("FileChooser.deleteFileButtonText", "Eliminar fichero");
					UIManager.put("FileChooser.filterLabelText", "Tipo");
					UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalles ");
					UIManager.put("FileChooser.fileSizeHeaderText","Talla");
					UIManager.put("FileChooser.fileDateHeaderText", "Fecha modificada");
					
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.showSaveDialog(database);
					File saveFile = fileChooser.getSelectedFile();					
					
					if(saveFile != null)
					{
						saveFile = new File(saveFile.toString() + ".xlsx");
						Workbook wb = new XSSFWorkbook();
				
						Sheet sheet = wb.createSheet("Clientes");
						
						Row rowColumn = sheet.createRow(0);
						
						for (int x = 0; x < database.getTable().getColumnCount(); x++)
						{
						   Cell cell = rowColumn.createCell(x);
						   cell.setCellValue(database.getTable().getColumnName(x));
						}
						  
						for (int i = 0; i < database.getTable().getColumnCount(); i++)
						{
							Cell cell = rowColumn.createCell(i);
							cell.setCellValue(database.getTable().getColumnName(i));
						}
						
						for (int j = 0; j < database.getTable().getRowCount(); j++)
						{
							Row row = sheet.createRow(j);
							for (int k = 0; k < database.getTable().getColumnCount(); k++)
							{
								Cell cell = row.createCell(k);
								if (database.getTable().getValueAt(j, k) != null)
								{
									cell.setCellValue(database.getTable().getValueAt(j, k).toString());
								}
							}
						}
						
						FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
						wb.write(out);
						wb.close();
						out.close();
						database.createFile(saveFile.toString());
					}
					else
					{
						//JOptionPane.showMessageDialog(null, "Error");
					}
				}
				
				catch(FileNotFoundException ex1)
				{
					System.out.println(ex1);
				}
				
				catch(IOException ex2)
				{
					System.out.println(ex2);
				}
		    }
		    
		    else if (confirmed == JOptionPane.NO_OPTION)
		    {
		    	database.setVisible(false);
		    	theView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		    }
		    	
		    else if (confirmed == JOptionPane.CANCEL_OPTION)
		    	theView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    
		    else
		    	theView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}


	/**
	 * Method that sets the graphics for the UI.
	 */
	public static void lookAndFeelSetter()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		
		catch (UnsupportedLookAndFeelException e)
		{
		}
		catch (ClassNotFoundException e)
		{
		}
		catch (InstantiationException e)
		{
		}
		catch (IllegalAccessException e)
		{
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}

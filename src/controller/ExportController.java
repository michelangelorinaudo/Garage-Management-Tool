package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Database;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

/**
 * Class that has the role of exporting the JTable into an Excel file.
 * @author MR
 *
 */
public class ExportController implements ActionListener
{

	private Database database;
	
	/**
	 * Class constructor.
	 * @param database
	 */
	public ExportController(Database database)
	{
		super();
		this.database = database;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			UIManager.put("FileChooser.saveDialogTitleText", "Guardar");
			UIManager.put("FileChooser.openDialogTitleText", "Abrir");
			UIManager.put("FileChooser.saveInLabelText", "Guardar en");
			UIManager.put("FileChooser.lookInLabelText", "Guardar en");
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

}

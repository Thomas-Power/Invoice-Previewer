import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class ExcelPreviewer3 {
	File file = new File(".\\Automated Accounts\\June Invoices");
	String[] invoiceStrings = file.list();
	File file2 = new File(".\\Automated Accounts\\");
	String[] monthStrings = file2.list();
	private JFrame frame;
	private JTextField textField;
	
	int printCount = 0;
	/**
	 * Launch the application.
	 */ 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcelPreviewer3 window = new ExcelPreviewer3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExcelPreviewer3() {
		initialize();
	}
	
	class ItemChangeListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          Object item = event.getItem();
	          // do something with object
	       }
	    }       
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 760, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAccountName = new JLabel("Account Name");
		
		
		JLabel lblMonth = new JLabel("Month");
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JComboBox comboBox = new JComboBox(invoiceStrings);
		JComboBox comboBox_1 = new JComboBox(monthStrings);
		
		comboBox_1.addItemListener(new ItemChangeListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					JComboBox comboBox_1 = (JComboBox)arg0.getSource();
					String accountName = comboBox.getSelectedItem().toString();
					String monthName = comboBox_1.getSelectedItem().toString();
					StringBuilder outputString = new StringBuilder();
					
					try {
						
						FileInputStream file = new FileInputStream(new File(".\\Automated Accounts\\" + monthName + "\\" + accountName));
						
						//Get the workbook instance for XLS file 
						XSSFWorkbook workbook = new XSSFWorkbook(file);
	
						//Get first sheet from the workbook
						XSSFSheet sheet = workbook.getSheetAt(0);
						//Iterate through each rows from first sheet
						Iterator<Row> rowIterator = sheet.iterator();
						int total = 0;
						for (int i=14; i<=51; i++){
							Row totalRow = sheet.getRow(i);
							int colCount = totalRow.getPhysicalNumberOfCells();
							if (totalRow != null) {
							    String cellValueMay = null;
							    for (int colIndex = 0; colIndex <= colCount; colIndex++) {
							      if (colIndex == 4) {
							        Cell totalCell = totalRow.getCell(colIndex);
							        if (totalCell != null) {
							          // Found column and there is value in the cell.
							          int cellTotal = (int) totalCell.getNumericCellValue();
							          total = cellTotal;
							          break;
							        }
							    }
							  }
							}
						}
						
						while(rowIterator.hasNext()) {
							Row row = rowIterator.next();
							
							//For each row, iterate through each columns
							Iterator<Cell> cellIterator = row.cellIterator();
							while(cellIterator.hasNext()) {
								
								Cell cell = cellIterator.next();
								
								switch(cell.getCellType()) {
									case Cell.CELL_TYPE_BOOLEAN:
										outputString.append(cell.getBooleanCellValue() + "\t\t");
										break;
									case Cell.CELL_TYPE_NUMERIC:
										outputString.append(cell.getNumericCellValue() + "\t\t");
										break;
									case Cell.CELL_TYPE_STRING:
										outputString.append(cell.getStringCellValue() + "\t\t");
										break;
								}
							}
							outputString.append("" + "\n");
						}
						file.close();
						String finalString = outputString.toString();
						finalString = finalString + "\t\t" + total;
						textArea.setText(finalString);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}});
		
		comboBox.addItemListener(new ItemChangeListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					JComboBox comboBox = (JComboBox)arg0.getSource();
					String accountName = comboBox.getSelectedItem().toString();
					String monthName = comboBox_1.getSelectedItem().toString();
					StringBuilder outputString = new StringBuilder();
					
					try {
						
						FileInputStream file = new FileInputStream(new File(".\\Automated Accounts\\" + monthName + "\\" + accountName));
						
						//Get the workbook instance for XLS file 
						XSSFWorkbook workbook = new XSSFWorkbook(file);
	
						//Get first sheet from the workbook
						XSSFSheet sheet = workbook.getSheetAt(0);
						//Iterate through each rows from first sheet
						Iterator<Row> rowIterator = sheet.iterator();
						int total = 0;
						for (int i=14; i<=51; i++){
							Row totalRow = sheet.getRow(i);
							int colCount = totalRow.getPhysicalNumberOfCells();
							if (totalRow != null) {
							    String cellValueMay = null;
							    for (int colIndex = 0; colIndex <= colCount; colIndex++) {
							      if (colIndex == 4) {
							        Cell totalCell = totalRow.getCell(colIndex);
							        if (totalCell != null) {
							          // Found column and there is value in the cell.
							          int cellTotal = (int) totalCell.getNumericCellValue();
							          total = total + cellTotal;
							          break;
							        }
							    }
							  }
							}
						}
						
						while(rowIterator.hasNext()) {
							Row row = rowIterator.next();
							
							//For each row, iterate through each columns
							Iterator<Cell> cellIterator = row.cellIterator();
							while(cellIterator.hasNext()) {
								
								Cell cell = cellIterator.next();
								
								switch(cell.getCellType()) {
									case Cell.CELL_TYPE_BOOLEAN:
										outputString.append(cell.getBooleanCellValue() + "\t\t");
										break;
									case Cell.CELL_TYPE_NUMERIC:
										outputString.append(cell.getNumericCellValue() + "\t\t");
										break;
									case Cell.CELL_TYPE_STRING:
										outputString.append(cell.getStringCellValue() + "\t\t");
										break;
								}
							}
							outputString.append("" + "\n");
						}
						file.close();
						String finalString = outputString.toString();
						finalString = finalString + "\t\t" + total;
						textArea.setText(finalString);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}});
		
		JButton btnOpenInExcel = new JButton("Open in Excel");
		btnOpenInExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountName = comboBox.getSelectedItem().toString();
				String monthName = comboBox_1.getSelectedItem().toString();
				String fileString = ".\\Automated Accounts\\" + monthName + "\\" + accountName;
				try{
					if(new File(fileString).exists()) {
				            Process p = Runtime
				               .getRuntime()
				               .exec("rundll32 url.dll,FileProtocolHandler " + fileString);
				            p.waitFor();

				        } else {

				            System.out.println("File does not exist");

				        }

				      } catch (Exception ex) {
				        ex.printStackTrace();
				      }
			}
		});
		
		JButton btnAddToPrint = new JButton("Add to Print Que");
		btnAddToPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newline = System.getProperty("line.separator");
				String accountName = comboBox.getSelectedItem().toString();
				String monthName = comboBox_1.getSelectedItem().toString();
				String fileString = ".\\Automated Accounts\\" + monthName + "\\" + accountName + "\t" + newline + "to print" + newline;
				try {
					FileUtils.writeStringToFile(new File(".\\Code\\printque.txt"), fileString, true);
					printCount = ++printCount;
					textField.setText(Integer.toString(printCount));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
		});
		
		JLabel lblToPrint = new JLabel("To Print");
		
		textField = new JTextField(Integer.toString(printCount));
		textField.setEditable(false);
		textField.setColumns(10);
		
		JButton btnPrintAll = new JButton("Print All");
		btnPrintAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(new File(".\\Code\\printAll.py").exists()) {
				            Process p = Runtime
				               .getRuntime()
				               .exec("rundll32 url.dll,FileProtocolHandler .\\Code\\printAll.py");
				            p.waitFor();
				            int printCount = 0;
				            textArea.setText(Integer.toString(printCount));

				        } else {

				            System.out.println("File does not exist");

				        }

				      } catch (Exception ex) {
				        ex.printStackTrace();
				      }
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAccountName)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMonth)
							.addGap(83)
							.addComponent(btnOpenInExcel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAddToPrint)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblToPrint)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnPrintAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(151, Short.MAX_VALUE))
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccountName)
						.addComponent(lblMonth)
						.addComponent(btnOpenInExcel)
						.addComponent(lblToPrint)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddToPrint)
						.addComponent(btnPrintAll))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}

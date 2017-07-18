import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import org.apache.commons.io.FileUtils;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceUpdater3 {
	
	File f = new File(".\\Automated Accounts\\Account Sheets");
	String[] invoiceStrings = f.list();
	String[] dayStrings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	String[] itemStrings = {"Full Fat Milk", "Low Fat Milk", "Slim Line Milk", "Super Full Fat Milk", "Super Low Fat Milk", "Soya Milk"};
	String[] unitStrings = {"1 Ltrs", "2 Ltrs", "500 ml", "Pergal"};

	private JFrame frmInvoiceUpdater;
	private JTextField itemQuantity;
	private JTextField itemValue;

	/**
	 * Launch the application.
	 */
	
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceUpdater3 window = new InvoiceUpdater3();
					window.frmInvoiceUpdater.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InvoiceUpdater3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInvoiceUpdater = new JFrame();
		frmInvoiceUpdater.setTitle("Invoice Updater");
		frmInvoiceUpdater.setBounds(100, 100, 550, 300);
		frmInvoiceUpdater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Statustext = new JLabel("Insert details");
		
		JLabel lblWeekDay = new JLabel("Week Day");
		
		JComboBox dayName = new JComboBox(dayStrings);
		
		JLabel lblProductName = new JLabel("Product Name");
		
		JComboBox itemName = new JComboBox(itemStrings);
		
		JLabel lblUnitSize = new JLabel("Unit Size");
		
		JComboBox unitName = new JComboBox(unitStrings);
		
		JLabel lblQuantity = new JLabel("Quantity");
		
		
		itemQuantity = new JTextField();
		itemQuantity.setColumns(10);
		
		JLabel lblPricePerUnit = new JLabel("Price per Unit");
		
		itemValue = new JTextField();
		itemValue.setColumns(10);
		
		
		
		JLabel lblFirmName = new JLabel("Account Name:");
		
		JComboBox companyName = new JComboBox(invoiceStrings);
		
		String ammendString = "";
				
				JRadioButton rdbtnAmmend = new JRadioButton("Ammend");
				JRadioButton rdbtnUpdate = new JRadioButton("Update");
				rdbtnUpdate.setSelected(true);
				
				rdbtnAmmend.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ammendString = "Ammend";
						rdbtnUpdate.setSelected(false);
					}
				});
				
				rdbtnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ammendString = "";
						rdbtnAmmend.setSelected(false);
					}
				});
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newline = System.getProperty("line.separator");
				File outputfile = new File("./updatelog.txt");
				String companyString = (String)companyName.getSelectedItem();
				String dayString = (String)dayName.getSelectedItem();
				String quantityString = itemQuantity.getText();
				String itemString = (String)itemName.getSelectedItem();
				String unitString = (String)unitName.getSelectedItem();
				String valueString = itemValue.getText();
				String outputString1 = companyString + " - " + getCurrentTimeStamp() + ammendString;
				
				String outputString2 = newline + dayString + " " + quantityString + " x " + itemString + " " + unitString + " " + "(€" + valueString + ")" + newline + newline;
				
				Statustext.setText(companyString + " Updated");
				
				try {
					FileUtils.writeStringToFile(new File("updatelog.txt"), outputString1, true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					FileUtils.writeStringToFile(new File("updatelog.txt"), outputString2, true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frmInvoiceUpdater.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirmName)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(Statustext)))
							.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
							.addComponent(companyName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(dayName, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblWeekDay, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblProductName)
									.addGap(56)
									.addComponent(lblUnitSize)
									.addGap(54)
									.addComponent(lblQuantity))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(itemName, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(unitName, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(itemQuantity, 0, 0, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblPricePerUnit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(rdbtnAmmend)
									.addComponent(rdbtnUpdate)
									.addComponent(btnUpdate))
								.addComponent(itemValue, 0, 0, Short.MAX_VALUE))))
					.addGap(45))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirmName)
						.addComponent(companyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeekDay)
						.addComponent(lblProductName)
						.addComponent(lblUnitSize)
						.addComponent(lblQuantity)
						.addComponent(lblPricePerUnit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dayName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(itemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(unitName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(itemQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(itemValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(rdbtnAmmend)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnUpdate)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Statustext)
						.addComponent(btnUpdate))
					.addContainerGap())
		);
		frmInvoiceUpdater.getContentPane().setLayout(groupLayout);
		
		}
}
	

	



package s2elab;


import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Label;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;

import javax.swing.AbstractListModel;

import java.awt.Component;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class View {

	private JFrame frame;
	private JTextField weightbox;
	private JTextField addressbox;
	private JTextField namebox;
	private JTextField traceID;
	private JTable table;
	private JScrollPane scrollPane1;
	private JTextField checkinID;
	private JTextField textField_1;
	private JTextField textField;
	private JScrollPane scrollPane2;
	private JTextField ResultID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Checkin.add("dokimi",0);
		Packages.getPostCodes();
		//Packages.add(2, "ekei", "YES", "SAPIKAS");
		//Packages[] pa = Packages.getPackages();
		
		//System.out.print(pa[2].ID);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Panels
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		JPanel Admin = new JPanel();
		JPanel Client = new JPanel();
		JPanel Courier = new JPanel();
		JPanel Worker = new JPanel();
		tabbedPane.addTab("Admin", null, Admin, null);
		Admin.setLayout(null);
		
		JLabel lblAveragePackageWeight = new JLabel("Average Package Weight");
		lblAveragePackageWeight.setBounds(25, 63, 167, 16);
		Admin.add(lblAveragePackageWeight);
		
		JLabel avgWeight = new JLabel(Packages.Weight("AVG"));
		avgWeight.setBounds(204, 63, 125, 16);
		Admin.add(avgWeight);
		
		JLabel lblMaximumPackageWeight = new JLabel("Maximum Package Weight");
		lblMaximumPackageWeight.setBounds(25, 34, 167, 16);
		Admin.add(lblMaximumPackageWeight);
		
		JLabel maxWeight = new JLabel(Packages.Weight("MAX"));
		maxWeight.setBounds(204, 34, 125, 16);
		Admin.add(maxWeight);
		
		JLabel lblMinimumPackageWeight = new JLabel("Minimum Package Weight");
		lblMinimumPackageWeight.setBounds(25, 91, 167, 16);
		Admin.add(lblMinimumPackageWeight);
		
		JLabel minWeight = new JLabel(Packages.Weight("MIN"));
		minWeight.setBounds(204, 91, 125, 16);
		Admin.add(minWeight);
		
		tabbedPane.addTab("Client",null, Client, null);
		tabbedPane.addTab("Courier",null, Courier, null);
		Courier.setLayout(null);
		
		//HashMap h = Packages.getHash();
		
		//Object[] st = h.keySet().toArray(new String[h.size()]);
		
		//String[] keys = (String[]) h.keySet().toArray(new String[0]);
		//Integer[][] dt = new Integer[][]{(Integer[]) h.keySet().toArray(),""};//{ (Integer[]) .keySet().toArray(), (Integer[]) Packages.getHash().entrySet().toArray() };
		
		//System.out.println(st[0].toString());
		
		table = new JTable(Packages.strArrMap(Packages.getHash()),new String[] {"Post code","Occurs"});
		scrollPane1 = new  JScrollPane(table);
		scrollPane1.setBounds(19, 133, 929, 547);
		Admin.add(scrollPane1);//*/
		
		textField = new JTextField();
		textField.setBounds(503, 72, 134, 28);
		Courier.add(textField);
		textField.setColumns(10);
		
		JLabel lblPackageId = new JLabel("Package ID:");
		lblPackageId.setBounds(369, 78, 90, 16);
		Courier.add(lblPackageId);
		
		JButton btnNewButton = new JButton("DELIVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckinsController.CheckinCtrl("Customer", Integer.parseInt(textField.getText()), "Delivered");
			}
		});
		btnNewButton.setBounds(369, 179, 268, 85);
		Courier.add(btnNewButton);
		tabbedPane.addTab("Worker",null, Worker, null);
		Client.setLayout(null);
		Worker.setLayout(null);
		
		Label label_4 = new Label("Put ID here :");
		label_4.setBounds(318, 32, 77, 16);
		Client.add(label_4);
		
		table = new JTable();
		scrollPane2 = new  JScrollPane(table);
		scrollPane2.setBounds(19, 58, 929, 546);
		Client.add(scrollPane2);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Weight");
		textField_1.setColumns(10);
		textField_1.setBounds(400, 26, 134, 28);
		Client.add(textField_1);
		
		JButton button_1 = new JButton("Retrieve Data");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Client.remove(scrollPane2);
				String[] s = {"Date", "Place", "Status"};
				table = new JTable(CheckinsController.getCheckInsCtrl(Integer.parseInt(textField_1.getText())),s);
				table.getTableHeader();
				scrollPane2 = new  JScrollPane(table);
				scrollPane2.setBounds(19, 58, 929, 546);
				Client.add(scrollPane2);
			}
		});
		button_1.setBounds(539, 26, 127, 29);
		Client.add(button_1);
		
		
		
		//scroll 1
		
		//table = new JTable();
		/*
		scrollPane2.setBounds(19, 58, 929, 546);
		table.getTableHeader();
		Client.add(scrollPane2);
		//Worker.add(table);
		Client.remove(scrollPane2);
		table = new JTable(data,s);
		table.getTableHeader();
		scrollPane_3= new  JScrollPane(table);
		scrollPane_3.setBounds(19, 58, 929, 546);
		Client.add(scrollPane_3);
		//scroll 1*/
		
		JTabbedPane WorkerPanel = new JTabbedPane(JTabbedPane.TOP);
		Worker.add(WorkerPanel, BorderLayout.CENTER);
		WorkerPanel.setBounds(6, 6, 991, 673);
		JPanel Trace = new JPanel();
		JPanel CheckinPanel = new JPanel();
		JPanel Add = new JPanel();
		WorkerPanel.addTab("Add",null,Add,null);
		Add.setLayout(null);
		
		Label weight = new Label("Weight");
		weight.setBounds(351, 89, 43, 16);
		Add.add(weight);
		
		weightbox = new JTextField();
		weightbox.setBounds(438, 89, 134, 28);
		weightbox.setToolTipText("Weight");
		Add.add(weightbox);
		weightbox.setColumns(10);
		
		JCheckBox fragile = new JCheckBox("Fragile");
		fragile.setBounds(444, 266, 128, 23);
		Add.add(fragile);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultID.setText(String.valueOf(Packages.add(Integer.parseInt(weightbox.getText()), addressbox.getText(),  fragile.isSelected() ? "YES" : "NO"  , namebox.getText())));
			}
		});
		btnSubmit.setBounds(461, 326, 88, 29);
		Add.add(btnSubmit);
		
		addressbox = new JTextField();
		addressbox.setToolTipText("Address");
		addressbox.setColumns(10);
		addressbox.setBounds(438, 145, 134, 28);
		Add.add(addressbox);
		
		namebox = new JTextField();
		namebox.setToolTipText("Fragile");
		namebox.setColumns(10);
		namebox.setBounds(438, 205, 134, 28);
		Add.add(namebox);
		
		Label address = new Label("Address");
		address.setBounds(351, 145, 51, 16);
		Add.add(address);
		
		Label name = new Label("Name");
		name.setBounds(358, 205, 36, 16);
		Add.add(name);
		
		ResultID = new JTextField();
		ResultID.setToolTipText("Weight");
		ResultID.setColumns(10);
		ResultID.setBounds(438, 498, 134, 28);
		Add.add(ResultID);
		WorkerPanel.addTab("Trace",null,Trace,null);
		WorkerPanel.addTab("Checkin",null,CheckinPanel,null);
		CheckinPanel.setLayout(null);
		
		Label label_1 = new Label("ID :");
		label_1.setBounds(377, 11, 77, 16);
		CheckinPanel.add(label_1);
		
		checkinID = new JTextField();
		checkinID.setBounds(459, 5, 171, 28);
		checkinID.setToolTipText("Weight");
		checkinID.setColumns(10);
		CheckinPanel.add(checkinID);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Athens Center (Syntagma)", "Athens West (Egaleo)", "Athens East  (Cholargos)", "Athens North (Marousi)", "Athens South (Kalithea)", "Korinthos", "Chalkis", "Thessaloniki Center", "Thessaloniki Kalamaria"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(357, 69, 273, 199);
		CheckinPanel.add(list);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Billing Information received", "Estimated to Arrive", "Departure Scan", "Arrival Scan", "Delivery Attempth", "Warehouse Scan", "Out for Delivery", "Delivered", "Import Scan", "Received"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(357, 302, 273, 199);
		CheckinPanel.add(list_1);//*/
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {		
				CheckinsController.CheckinCtrl(list.getSelectedValue().toString(), Integer.parseInt(checkinID.getText()), list_1.getSelectedValue().toString());
			}
		});
		button.setBounds(451, 534, 88, 29);
		CheckinPanel.add(button);
		
		Label label_2 = new Label("Place Selection");
		label_2.setBounds(195, 154, 77, 16);
		CheckinPanel.add(label_2);
		
		Label label_3 = new Label("Status :");
		label_3.setBounds(195, 386, 77, 16);
		CheckinPanel.add(label_3);
		Worker.add(WorkerPanel);
		Trace.setLayout(null);
		
		Label label = new Label("Put ID here :");
		label.setBounds(311, 11, 77, 16);
		Trace.add(label);
		
		traceID = new JTextField();
		traceID.setBounds(393, 5, 134, 28);
		traceID.setToolTipText("Weight");
		traceID.setColumns(10);
		Trace.add(traceID);
		
		table = new JTable();
		scrollPane1 = new  JScrollPane(table);
		scrollPane1.setBounds(19, 58, 929, 546);
		Trace.add(scrollPane1);
		
		JButton retrievebtn = new JButton("Retrieve Data");
		retrievebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trace.remove(scrollPane1);
				String[] s = {"Date", "Place", "Status"};
				table = new JTable(CheckinsController.getCheckInsCtrl(Integer.parseInt(traceID.getText())),s);
				table.getTableHeader();
				scrollPane1 = new  JScrollPane(table);
				scrollPane1.setBounds(19, 58, 929, 546);
				Trace.add(scrollPane1);
			}
		});
		retrievebtn.setBounds(532, 5, 127, 29);
		Trace.add(retrievebtn);
		
		
		
		
		/*String[] st = {"Date", "Place", "Status"};
		Checkin[] ct = Checkin.getCheckins(0);
		String[][] datat = new String[ct.length][3];
		
		
		for(int i = 0;i < ct.length;i++)
		{
			datat[i] = ct[i].listdata;
		}
		table = new JTable();
		JScrollPane scrollPane= new  JScrollPane(table);
		scrollPane.setBounds(19, 58, 929, 546);
		table.getTableHeader();
		Trace.add(scrollPane);
		//Worker.add(table);
		Trace.remove(scrollPane);
		table = new JTable(datat,st);
		table.getTableHeader();
		scrollPane_1= new  JScrollPane(table);
		scrollPane_1.setBounds(19, 58, 929, 546);
		Trace.add(scrollPane_1);*/
		
	}
	protected JTextField getWeightbox_1() {
		return traceID;
	}
}

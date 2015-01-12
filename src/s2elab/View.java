package s2elab;

import com.mysql.jdbc.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.Button;

import javax.swing.JButton;

import java.awt.Label;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class View {

	private JFrame frame;
	private JTextField weightbox;
	private JTextField addressbox;
	private JTextField namebox;
	private JTextField IDbox;
	private JTextField weightbox_1;

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
		
		java.sql.Connection conn = null;
		Statement stmt = null;

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dadme","root","");
			ResultSet rs = conn.createStatement().executeQuery("Select * from  packages");
			
			while(rs.next()){
				System.out.println(rs.getString("address"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("gamithike to syban");
			
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 718, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Panels
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		JPanel Admin = new JPanel();
		JPanel Client = new JPanel();
		JPanel Courier = new JPanel();
		JPanel Worker = new JPanel();
		tabbedPane.addTab("Admin", null, Admin, null);
		tabbedPane.addTab("Client",null, Client, null);
		tabbedPane.addTab("Courier", null, Courier, null);
		tabbedPane.addTab("Worker",null, Worker, null);
		Worker.setLayout(null);
		
		JCheckBox fragile = new JCheckBox("Fragile");
		fragile.setBounds(106, 235, 128, 23);
		Worker.add(fragile);
		
		weightbox = new JTextField();
		weightbox.setBounds(106, 116, 134, 28);
		weightbox.setToolTipText("Weight");
		Worker.add(weightbox);
		weightbox.setColumns(10);
		
		Label weight = new Label("Weight");
		weight.setBounds(22, 116, 51, 28);
		Worker.add(weight);
		
		addressbox = new JTextField();
		addressbox.setToolTipText("Address");
		addressbox.setColumns(10);
		addressbox.setBounds(106, 156, 134, 28);
		Worker.add(addressbox);
		
		namebox = new JTextField();
		namebox.setToolTipText("Fragile");
		namebox.setColumns(10);
		namebox.setBounds(106, 196, 134, 28);
		Worker.add(namebox);
		
		Label address = new Label("Address");
		address.setBounds(22, 156, 51, 28);
		Worker.add(address);
		
		Label name = new Label("Name");
		name.setBounds(22, 196, 51, 28);
		Worker.add(name);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(63, 293, 117, 29);
		Worker.add(btnSubmit);
		
		IDbox = new JTextField();
		IDbox.setBounds(391, 22, 151, 28);
		Worker.add(IDbox);
		
		
		
		JButton retrievebtn = new JButton("Retrieve Data");
		retrievebtn.setBounds(554, 23, 117, 29);
		Worker.add(retrievebtn);
		
		Label label = new Label("Put ID here");
		label.setBounds(296, 24, 86, 28);
		Worker.add(label);
		
		weightbox_1 = new JTextField();
		weightbox_1.setToolTipText("Weight");
		weightbox_1.setColumns(10);
		weightbox_1.setBounds(106, 116, 134, 28);
		Worker.add(weightbox_1);
		
		
		
	}
}

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class Penonton {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JLabel jtxtSitNumber;
	private JTextField textField_1;
	private JLabel jxtxNamaAkhir;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel jtxtNamaAwalan;
	private JLabel jtxtUmur;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel jtxtJenisKelamin;
	private JLabel jtxtJamTayang;
	private JTextField textField_6;
	private JTextField textField_7;
	private JLabel jtxtFilm;
	private JButton btnPrint;
	private JButton btnReset;
	private JButton btnExit;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	
	public void updateTable()
	{
		conn = DataPenonton.ConnectDB();
				
		if (conn !=null)
		{
			String sql = "Pilih Studio, SitNumber, Nama Awalan, Nama Akhir, Jenis Kelamin, Umur, Film, Jam Tayang";
		
		
		try
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object[7];
			
			while (rs.next()){
				columnData [0] = rs.getString("Studio");
				columnData [1] = rs.getString("SitNumber");
				columnData [2] = rs.getString("Nama Awalan");
				columnData [3] = rs.getString("Nama Akhir");
				columnData [4] = rs.getString("Jenis Kelamin");
				columnData [5] = rs.getString("Umur");
				columnData [6] = rs.getString("Film");
				columnData [7] = rs.getString("Jam Tayang");
				
				model.addRow(columnData);
			}
		}
		 catch(Exception e)
		    {
			    JOptionPane.showMessageDialog(null, e);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Penonton window = new Penonton();
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
	public Penonton() {
		initialize();
		
		conn = DataPenonton.ConnectDB();
		Object col[] = {"Studio", "SitNumber", "Nama Awalan", "Nama Akhir", "Jenis Kelamin", "Umur", "Film", "Jam Tayang"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel jtxtStudio = new JLabel("Studio");
		jtxtStudio.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtStudio.setBounds(45, 120, 182, 26);
		frame.getContentPane().add(jtxtStudio);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(296, 108, 215, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String sql = "INSERT INTO penonton(Studio, SitNumber, Nama Awalan, Nama Akhir, Jenis Kelamin, Umur, Film, Jam Tayang)VALUES(?,?,?,?,?,?,?)";
			
			try
			{
		    pst = conn.prepareStatement(sql);
			 pst.setString(1, textField.getText());
			 pst.setString(2, textField_1.getText());
			 pst.setString(3, textField_3.getText());
			 pst.setString(4, textField_2.getText());
			 pst.setString(5, textField_5.getText());
			 pst.setString(6, textField_4.getText());
			 pst.setString(7, textField_7.getText());
			 pst.setString(8, textField_6.getText());
			
			pst.execute();
			
			rs.close();
			pst.close();
			}
			catch (Exception ev)
			{
				JOptionPane.showMessageDialog(null, "System Update Complete");
			}
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model .addRow(new Object[] {
					
				textField.getText(),
				textField_1.getText(),
				textField_3.getText(),
				textField_2.getText(),
				textField_5.getText(),
				textField_4.getText(),
				textField_7.getText(),
				textField_6.getText(),
					
			});
			if (table.getSelectedRow() == -1){
				if (table.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Update Penonton Terkonfirmasi","Sistem Basisdata Penonton",
						JOptionPane.OK_OPTION);
				}
			}
				
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddNew.setBounds(33, 573, 215, 52);
		frame.getContentPane().add(btnAddNew);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(613, 108, 606, 381);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Studio", "Sit Number", "Nama Awalan", "Nama Akhir", "Jenis Kelamin", "Umur", "Film", "Jam Tayang"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		jtxtSitNumber = new JLabel("Sit Number");
		jtxtSitNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtSitNumber.setBounds(45, 169, 182, 26);
		frame.getContentPane().add(jtxtSitNumber);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(296, 157, 215, 38);
		frame.getContentPane().add(textField_1);
		
		jxtxNamaAkhir = new JLabel("Nama Akhir");
		jxtxNamaAkhir.setFont(new Font("Tahoma", Font.BOLD, 18));
		jxtxNamaAkhir.setBounds(45, 267, 182, 26);
		frame.getContentPane().add(jxtxNamaAkhir);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(296, 255, 215, 38);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(296, 206, 215, 38);
		frame.getContentPane().add(textField_3);
		
		jtxtNamaAwalan = new JLabel("Nama Awalan");
		jtxtNamaAwalan.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtNamaAwalan.setBounds(45, 218, 182, 26);
		frame.getContentPane().add(jtxtNamaAwalan);
		
		jtxtUmur = new JLabel("Umur");
		jtxtUmur.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtUmur.setBounds(45, 365, 182, 26);
		frame.getContentPane().add(jtxtUmur);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(296, 353, 215, 38);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(296, 304, 215, 38);
		frame.getContentPane().add(textField_5);
		
		jtxtJenisKelamin = new JLabel("Jenis Kelamin");
		jtxtJenisKelamin.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtJenisKelamin.setBounds(45, 316, 182, 26);
		frame.getContentPane().add(jtxtJenisKelamin);
		
		jtxtJamTayang = new JLabel("Jam Tayang");
		jtxtJamTayang.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtJamTayang.setBounds(45, 463, 182, 26);
		frame.getContentPane().add(jtxtJamTayang);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(296, 451, 215, 38);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_7.setColumns(10);
		textField_7.setBounds(296, 402, 215, 38);
		frame.getContentPane().add(textField_7);
		
		jtxtFilm = new JLabel("Film");
		jtxtFilm.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtFilm.setBounds(45, 414, 182, 26);
		frame.getContentPane().add(jtxtFilm);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				
				MessageFormat header = new MessageFormat("Printing on Progress");
				MessageFormat footer = new MessageFormat("Page  {0, number, integer}");
				
				try
				{
					table.print();
				}
				catch(java.awt.print.PrinterException ev) {
					System.err.format("No Printer found", ev.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(276, 573, 215, 52);
		frame.getContentPane().add(btnPrint);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText(null);
				textField_1.setText(null);
				textField_3.setText(null);
				textField_2.setText(null);
				textField_5.setText(null);
				textField_4.setText(null);
				textField_7.setText(null);
				textField_6.setText(null);
				
				
				
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(518, 573, 215, 52);
		frame.getContentPane().add(btnReset);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame =new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm to Exit", "Sistem Manajemen Basis Data Penonton",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(762, 573, 215, 52);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Sistem Manajemen Basis Data Penonton ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(378, 30, 725, 44);
		frame.getContentPane().add(lblNewLabel);
	}
}

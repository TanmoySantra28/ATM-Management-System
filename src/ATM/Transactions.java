package ATM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Transactions {
	
	public Transactions(Database database, int userID) {
		JFrame frame = new JFrame(userID+" - Transactions");
		frame.setLayout(new BorderLayout());
		
		String[][] data = TransactionsDatabase.getUserTransactions(database, userID);
		String[] columnNames = {"ID", "Operation", "Amount", "Date", "Time"};
		
		JScrollPane sp = new JScrollPane(jTable(data, columnNames));
		sp.setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
		sp.setBackground(null);
		sp.getViewport().setBackground(null);
		frame.add(sp, BorderLayout.CENTER);
		
		JLabel title = GUIConstants.jLabel(userID+" - Transactions", 30);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
		frame.add(title, BorderLayout.NORTH);
		
		frame.setSize(1200, 700);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(GUIConstants.backgroundColor);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	private static JTable jTable(String[][] data, String[] columnNames) {
		JTable table = new JTable(data, columnNames);
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(40);
		table.setBackground(null);
		
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				setHorizontalAlignment(JLabel.CENTER);
				setFont(new Font("SansSerif", Font.PLAIN, 20));
				if (hasFocus) {
					setBorder(null);
				}
				if (row%2==0) {
					setBackground(Color.white);
				} else {
					setBackground(GUIConstants.foregroundColor);
				}
				return this;
			}
		};
		
		for (int i=0;i<table.getColumnModel().getColumnCount();i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				setHorizontalAlignment(JLabel.CENTER);
				setFont(new Font("SansSerif", Font.BOLD, 20));
				setBackground(GUIConstants.mainColor);
				setForeground(Color.white);
				setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				return this;
			}
		};
		
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		table.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2,
				GUIConstants.mainColor));
		table.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2,
				GUIConstants.mainColor));
		table.setGridColor(GUIConstants.mainColor);
		table.setRowSelectionAllowed(false);
		return table;
	}

}

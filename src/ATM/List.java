package ATM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class List {
	
	public List(Database database, User user) {
		JFrame frame = new JFrame("ATM");
		frame.setLayout(new BorderLayout());
		
		JLabel title = GUIConstants.jLabel("Welcome to ATM", 30);
		title.setBorder(BorderFactory.createEmptyBorder(40, 40, 15, 40));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(5, 1, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 40, 40, 40));
		
		JButton deposit = GUIConstants.jButton("Deposit");
		deposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Operation("Deposit", user, database);
			}
		});
		panel.add(deposit);
		
		JButton withdraw = GUIConstants.jButton("Withdraw");
		withdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Operation("Withdraw", user, database);
			}
		});
		panel.add(withdraw);
		
		JButton balance = GUIConstants.jButton("Balance");
		balance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Balance(user, database);
			}
		});
		panel.add(balance);
		
		JButton transactions = GUIConstants.jButton("Transactions");
		transactions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Transactions(database, user.getID());
			}
		});
		panel.add(transactions);
		
		JButton edit = GUIConstants.jButton("Edit My Data");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserData(false, database, user.getID());
			}
		});
		panel.add(edit);
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setSize(400, 520);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(GUIConstants.backgroundColor);
		frame.setVisible(true);
	}

}

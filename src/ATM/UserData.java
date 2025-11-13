package ATM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserData {
	
	private JLabel id;
	private JTextField firstName, lastName, birthDate, email, phoneNumber;
	private JPasswordField pincode, confirmpincode;
	
	public UserData(boolean newAcc, Database database, int ID) {
		JFrame frame = new JFrame("ATM");
		frame.setLayout(new BorderLayout());
		
		JLabel title = GUIConstants.jLabel("Welcome to ATM", 30);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(9, 2, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
		
		JLabel lb1 = GUIConstants.jLabel("ID:", 23);
		panel.add(lb1);
		id = GUIConstants.jLabel(String.valueOf(ID), 23);
		panel.add(id);
		JLabel lb2 = GUIConstants.jLabel("First Name:", 23);
		panel.add(lb2);
		firstName = GUIConstants.jTextField();
		panel.add(firstName);
		JLabel lb3 = GUIConstants.jLabel("Last Name:", 23);
		panel.add(lb3);
		lastName = GUIConstants.jTextField();
		panel.add(lastName);
		JLabel lb4 = GUIConstants.jLabel("Birth Date (yyyy-dd-MM):", 23);
		panel.add(lb4);
		birthDate = GUIConstants.jTextField();
		panel.add(birthDate);
		JLabel lb5 = GUIConstants.jLabel("Email:", 23);
		panel.add(lb5);
		email = GUIConstants.jTextField();
		panel.add(email);
		JLabel lb6 = GUIConstants.jLabel("Phone Number:", 23);
		panel.add(lb6);
		phoneNumber = GUIConstants.jTextField();
		panel.add(phoneNumber);
		JLabel lb7 = GUIConstants.jLabel("PIN Code:", 23);
		panel.add(lb7);
		pincode = GUIConstants.jPasswordField();
		panel.add(pincode);
		JLabel lb8 = GUIConstants.jLabel("Confirm PIN Code:", 23);
		panel.add(lb8);
		confirmpincode = GUIConstants.jPasswordField();
		panel.add(confirmpincode);
		
		JButton confirm = GUIConstants.jButton("Continue");
		
		if (newAcc) {
			JButton login = GUIConstants.jButton("Already have an account");
			login.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					new Login(database);
					frame.dispose();
				}
			});
			panel.add(login);
			
			confirm.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String firstNameIn = firstName.getText();
					String lastNameIn = lastName.getText();
					String birthDateIn = birthDate.getText();
					String emailIn = email.getText();
					String phoneNumberIn = phoneNumber.getText();
					int PINCodeIn, confirmPINCodeIn;
					
					if (firstNameIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "First Name cannot be empty");
						return;
					}
					if (lastNameIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Last Name cannot be empty");
						return;
					}
					if (birthDateIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Birth Date cannot be empty");
						return;
					}
					try {
						LocalDate.parse(birthDate.getText(), DateTimeFormatter.ofPattern("yyyy-dd-MM"));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame, "BirthDate format doesn't match");
						return;
					}
					if (emailIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Email cannot be empty");
						return;
					}
					if (phoneNumberIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Phone Number cannot be empty");
						return;
					}
					try {
						PINCodeIn = Integer.parseInt(pincode.getText());
						confirmPINCodeIn = Integer.parseInt(confirmpincode.getText());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, "PIN Code must be 4 digits (int)");
						return;
					}
					if (PINCodeIn!=confirmPINCodeIn) {
						JOptionPane.showMessageDialog(frame, "PIN Code doesn't match");
						return;
					}
					
					User user = new User();
					user.setID(ID);
					user.setFirstName(firstNameIn);
					user.setLastName(lastNameIn);
					user.setBirthDate(birthDateIn);
					user.setEmail(emailIn);
					user.setPhoneNumber(phoneNumberIn);
					user.setPINCode(PINCodeIn);
					user.setBalance(0);
					UsersDatabase.CreateNewAcc(user, database);
					new List(database, user);
					frame.dispose();
				}
			});
		} else {
			
			User user = UsersDatabase.getUserByID(ID, database);
			id.setText(String.valueOf(user.getID()));
			firstName.setText(user.getFirstName());
			lastName.setText(user.getLastName());
			birthDate.setText(user.getBirthDate());
			email.setText(user.getEmail());
			phoneNumber.setText(user.getPhoneNumber());
			DecimalFormat format = new DecimalFormat("0000");
			pincode.setText(format.format(user.getPINCode()));
			confirmpincode.setText(format.format(user.getPINCode()));
			
			JButton cancel = GUIConstants.jButton("Cancel");
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			panel.add(cancel);
			
			confirm.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent e) {
					String firstNameIn = firstName.getText();
					String lastNameIn = lastName.getText();
					String birthDateIn = birthDate.getText();
					String emailIn = email.getText();
					String phoneNumberIn = phoneNumber.getText();
					int PINCodeIn, confirmPINCodeIn;
					
					if (firstNameIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "First Name cannot be empty");
						return;
					}
					if (lastNameIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Last Name cannot be empty");
						return;
					}
					if (birthDateIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Birth Date cannot be empty");
						return;
					}
					try {
						LocalDate.parse(birthDate.getText(), DateTimeFormatter.ofPattern("yyyy-dd-MM"));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame, "BirthDate format doesn't match");
						return;
					}
					if (emailIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Email cannot be empty");
						return;
					}
					if (phoneNumberIn.equals("")) {
						JOptionPane.showMessageDialog(frame, "Phone Number cannot be empty");
						return;
					}
					try {
						PINCodeIn = Integer.parseInt(pincode.getText());
						confirmPINCodeIn = Integer.parseInt(confirmpincode.getText());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, "PIN Code must be 4 digits (int)");
						return;
					}
					if (PINCodeIn!=confirmPINCodeIn) {
						JOptionPane.showMessageDialog(frame, "PIN Code doesn't match");
						return;
					}
					
					user.setFirstName(firstNameIn);
					user.setLastName(lastNameIn);
					user.setBirthDate(birthDateIn);
					user.setEmail(emailIn);
					user.setPhoneNumber(phoneNumberIn);
					user.setPINCode(PINCodeIn);
					UsersDatabase.updateUserData(user, database);
					JOptionPane.showMessageDialog(null, "Data updated successfully");
					frame.dispose();
				}
			});
		}
		
		panel.add(confirm);
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setSize(750, 720);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(GUIConstants.backgroundColor);
		frame.setVisible(true);
	}
	
}
package ATM;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Balance {
	
	public Balance(User user, Database database) {
		JFrame frame = new JFrame("ATM");
		frame.setLayout(new BorderLayout());
		
		JLabel content = GUIConstants.jLabel("Your current balance is: "+
						user.getBalance()+" $", 30);
		content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		frame.add(content, BorderLayout.CENTER);
		
		frame.setSize(650, 170);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(GUIConstants.backgroundColor);
		frame.setVisible(true);
	}

}

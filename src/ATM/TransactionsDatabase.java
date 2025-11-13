package ATM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionsDatabase {
	
	private static ArrayList<Transaction> getAllTransactions(Database database) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		ArrayList<Integer> usersIDs = new ArrayList<>();
		String select = "SELECT * FROM `transactions`;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setID(rs.getInt("ID"));
				t.setAmount(rs.getDouble("Amount"));
				t.setDateTime(rs.getString("DateTime"));
				usersIDs.add(rs.getInt("UserID"));
				transactions.add(t);
			}
			
			for (int i=0;i<transactions.size();i++) {
				User user = UsersDatabase.getUserByID(usersIDs.get(i), database);
				transactions.get(i).setUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
	public static int getNextID(Database database) {
		int ID = 0;
		ArrayList<Transaction> transactions = getAllTransactions(database);
		int size = transactions.size();
		if (size!=0) {
			Transaction last = transactions.get(size-1);
			ID = last.getID()+1;
		}
		return ID;
	}
	
	public static void saveTransaction(Transaction t, Database database) {
		String insert = "INSERT INTO `transactions`(`ID`, `Amount`, `DateTime`, `UserID`)"
				+ " VALUES ('"+t.getID()+"','"+t.getAmount()+"','"+t.getDateTime()+"','"
				+t.getUser().getID()+"');";
		try {
			database.getStatement().execute(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] getUserTransactions(Database database, int id) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		String select = "SELECT * FROM `transactions` WHERE `UserID` = "+id+" ;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setID(rs.getInt("ID"));
				t.setAmount(rs.getDouble("Amount"));
				t.setDateTime(rs.getString("DateTime"));
				transactions.add(t);
			}
			
			User user = UsersDatabase.getUserByID(id, database);
			for (Transaction t : transactions) {
				t.setUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String[][] data = new String[transactions.size()][5];
		for (int j=0;j<transactions.size();j++) {
			data[j][0] = String.valueOf(transactions.get(j).getID());
			if (transactions.get(j).getAmount()>0) {
				data[j][1] = "Deposit";
			} else {
				data[j][1] = "Withdraw";
			}
			data[j][2] = String.valueOf(Math.abs(transactions.get(j).getAmount()));
			data[j][3] = transactions.get(j).getDate();
			data[j][4] = transactions.get(j).getTime();
		}
		return data;
	}

}

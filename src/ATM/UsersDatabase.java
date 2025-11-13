package ATM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDatabase {
	
	public static void CreateNewAcc(User user, Database database) {
		String insert = "INSERT INTO `users`(`ID`, `FirstName`, `LastName`, `BirthDate`, "
				+ "`Email`, `PhoneNumber`, `PINCode`, `Balance`) VALUES "
				+ "('"+user.getID()+"','"+user.getFirstName()+"','"+user.getLastName()+"',"
						+ "'"+user.getBirthDate()+"','"+user.getEmail()+"','"+
				user.getPhoneNumber()+"','"+user.getPINCode()+"','0');";
		try {
			database.getStatement().execute(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<User> getAllUsers(Database database) {
		ArrayList<User> users = new ArrayList<>();
		String select = "SELECT * FROM `users`;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				User user = new User();
				user.setID(rs.getInt("ID"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setBirthDate(rs.getString("BirthDate"));
				user.setEmail(rs.getString("Email"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setPINCode(rs.getInt("PINCode"));
				user.setBalance(rs.getDouble("Balance"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static int getNextID(Database database) {
		int ID = 1000000;
		ArrayList<User> users = getAllUsers(database);
		int size = users.size();
		if (size!=0) {
			User user = users.get(size-1);
			ID = user.getID()+1;
		}
		return ID;
	}
	
	public static boolean login(int id, int pin, Database database) {
		boolean login = false;
		String select = "SELECT `ID`, `PINCode` FROM `users` WHERE `ID` = "+id+" ;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			int ID = rs.getInt("ID");
			int PIN = rs.getInt("PINCode");
			if (id==ID && pin==PIN) login = true;
		} catch (SQLException e) {}
		return login;
	}
	
	public static User getUser(int id, int pin, Database database) {
		User user = new User();
		String select = "SELECT * FROM `users` WHERE `ID` = "+id+" ;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			int PINCode = rs.getInt("PINCode");
			if (PINCode==pin) {
				user.setID(id);
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setBirthDate(rs.getString("BirthDate"));
				user.setEmail(rs.getString("Email"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setPINCode(PINCode);
				user.setBalance(rs.getDouble("Balance"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static User getUserByID(int id, Database database) {
		User user = new User();
		String select = "SELECT * FROM `users` WHERE `ID` = "+id+" ;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			user.setID(id);
			user.setFirstName(rs.getString("FirstName"));
			user.setLastName(rs.getString("LastName"));
			user.setBirthDate(rs.getString("BirthDate"));
			user.setEmail(rs.getString("Email"));
			user.setPhoneNumber(rs.getString("PhoneNumber"));
			user.setPINCode(rs.getInt("PINCode"));
			user.setBalance(rs.getDouble("Balance"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void updateUserBalance(User u, Database database) {
		String update = "UPDATE `users` SET `Balance`='"+u.getBalance()+"' "
				+ "WHERE `ID` = "+u.getID()+" ;";
		try {
			database.getStatement().execute(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateUserData(User u, Database database) {
		String update = "UPDATE `users` SET `FirstName`='"+u.getFirstName()+"',"
				+ "`LastName`='"+u.getLastName()+"',`BirthDate`='"+u.getBirthDate()+"',"
						+ "`Email`='"+u.getEmail()+"',`PhoneNumber`='"+u.getPhoneNumber()
						+"',`PINCode`='"+u.getPINCode()+"' WHERE `ID` = "+u.getID()+" ;";
		try {
			database.getStatement().execute(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

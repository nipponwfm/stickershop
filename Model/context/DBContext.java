package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DBContext {
	private final String server = "DONGKTJ\\SQLEXPRESS";
	private final String db = "Java";
	private final int port = 1433;
	private final String user = "dongdeptrai";
	private final String pwd = "511551";
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + db;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, user, pwd);
	}

//	public static void main(String[] args) {
//		var server = "DONGKTJ\\SQLEXPRESS";
//		var db = "Java";
//		var port = 1433;
//		var user = "dongdeptrai";
//		var pwd = "511551";
//		SQLServerDataSource ds = new SQLServerDataSource();
//		ds.setDatabaseName(db);
//		ds.setServerName(server);
//		ds.setPortNumber(port);
//		ds.setUser(user);
//		ds.setPassword(pwd);
//		try (Connection conn = ds.getConnection()) {
//			System.out.print("THANHCONG");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
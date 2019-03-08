import java.sql.*;

public class JDBCExample {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/databasename";
	// Database credentials
	static final String USER = "username";
	static final String PASS = "password";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Step 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql; sql = "select id, firstname, lastname, from users";
			ResultSet rs = stmt.executeQuery(sql);
			// Step 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				int id = rs.getInt("id");
				String first = rs.getString("firstname");
				String last = rs.getString("lastname");
				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Firstname:" + first);
				System.out.println(". Lastname: " + last);
			}
			// Step 6: Clean-up enviroment
			rs.close();
			stmt.close();
			conn.close();
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// Finally block used to close resources
			try {
				if(stmt != null)
					stmt.close();
					conn.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
	}
	
}

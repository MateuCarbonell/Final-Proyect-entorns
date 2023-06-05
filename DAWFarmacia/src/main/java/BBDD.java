import java.sql.*;
public class BBDD {

	private Connection con;
	private Statement st;
	
	public void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia","root","");
			st = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.print("Error a BBDD .connection.Class "+ e.getMessage());
		}
	}

	public ResultSet loadSelect(String query) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.print("Error a BBDD .load.Class "+ e.getMessage());
		}
		return rs;
	}
	
	public void update(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.print("Error a BBDD .exeUpdate.Class "+ e.getMessage());
		}
	}
	
	public Statement getStatement() throws SQLException {
		if(con != null) {
			return con.createStatement();
		}else {
			throw new SQLException("No se ha establecido una conexion a la BBDD");
		}
	}
}

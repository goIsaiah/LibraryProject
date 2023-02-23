package Databases;
import java . sql .*;


public class SQLMain {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/example " ;
		String user = "root" ;
		String password = "1977" ;
		String query = "show tables;" ;
		try {
			Connection con = DriverManager.getConnection ( url , user , password ) ;
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery( query );
			while (result.next()) {
				String data = " " ;
				for ( int i = 1; i < 2; i ++) {
					data += data + result . getString ( i ) ;
				} 
				System . out . println (data) ;
				}
			}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

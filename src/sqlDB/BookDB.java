<<<<<<< HEAD
package sqldb;
=======
package sqlDB;
>>>>>>> refs/remotes/origin/pwDB

import java.sql.SQLException;

public class BookDB extends LibraryDB implements DB{

	public BookDB(String str) throws SQLException {
		super(str);
	}

	@Override
	public <E> void addItem(E item) {
		
	}

	@Override
	public <E> void removeItem(E item) {
		
	}

	@Override
	public <E> String getItem(E item) {
		return null;
	}

	@Override
	public void setPassword(String psw) {
		
	}
	
}

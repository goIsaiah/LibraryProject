package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import Databases.DBType_enum;
import Databases.DBUserInfo;
import Databases.DBUtil;

class DBUrserInfoTests {

	@Test
	void test() {
		
		try {
			DBUserInfo info = new DBUserInfo();
			info.setConnection(DBUtil.getConnection(DBType_enum.ONLINE));
		String str = ':'+	 info.getPhotoUrl(1) +':'; 
		System.out.println(str);
		} catch (SQLException e) {
		}
		
	}

}

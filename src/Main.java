import GUI.*;
import Database.*;

import java.sql.Connection;

public class Main{
	public static void main(String[] args) throws DBConnectionFailException {
		try {
			//Create a connection to the database
			Connection conn = JDBC.getConnection();
			LoadingPage loadingPage = new LoadingPage();			
			
		}
		catch (Exception e) {
			throw new DBConnectionFailException("Fail to connect to database or access loading page!");
		}
	}
}
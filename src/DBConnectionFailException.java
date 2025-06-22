import javax.swing.JOptionPane;

public class DBConnectionFailException extends Exception{
	
	DBConnectionFailException(String message){
		super(message);
		JOptionPane.showMessageDialog(null, "Oops! We couldn't start the application due to a connection issue.\n" +
			    "Please check your internet or database settings and try again.","Connection Error", JOptionPane.ERROR_MESSAGE);
	}
}

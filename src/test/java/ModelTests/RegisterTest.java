import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import main.java.model.LoginModel;
import org.junit.After;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class RegisterTest{

	Connection conection;
	 String string1 = "test10101010";
	 String string2 = "anothertest101";


	@Before
	public void addUserToTheDatabase() throws SQLException{
		LoginModel loginModel = new LoginModel();
		loginModel.isRegister(string1,string2);
	}

	@Test
	public void checkIfUserHasBeenAddedToTheDatabase() {
		LoginModel loginModel = new LoginModel();
		try {
			assertEquals(true,loginModel.isLogin(string1,string2));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	@After
	public void deleteUserFromDatabase() throws SQLException{
		deleteUser(string1,string2);
	}


	public void deleteUser(String username, String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "DELETE  FROM tasks WHERE username = ? AND password = ?";
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}



}
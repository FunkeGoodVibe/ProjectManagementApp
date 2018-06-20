import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.model.LoginModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginTest{
		Connection conection;

	@Test
	public void loginUserToApplicationWithCorrectUsernameAndPassword() {
		LoginModel loginModel = new LoginModel();
		try {
			assertEquals(true,loginModel.isLogin("a","a"));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void loginUserToApplicationWithIncorrectUsernameAndPassword() {
		LoginModel loginModel = new LoginModel();
		try {
			assertEquals(false,loginModel.isLogin("doesn't","exsist"));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void loginUserToApplicationWithCorrectUsernameAndFalsePassword() {
		LoginModel loginModel = new LoginModel();
		try {
			assertEquals(false,loginModel.isLogin("a","exsist"));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void loginUserToApplicationWithIncorrectUsernameAndCorrectPassword() {
		LoginModel loginModel = new LoginModel();
		try {
			assertEquals(false,loginModel.isLogin("pop","a"));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Test
	public void loginUserToApplicationWithNoUsernameAndNoPassword() {
		LoginModel loginModel = new LoginModel();
		try {
			assertEquals(false,loginModel.isLogin("",""));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

/*
	public void registerUserToApplicationWithNoUsernameAndNoPassword() {
			LoginModel loginModel = new LoginModel();
		try {
   			 loginModel.isRegister("a","a");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

}
*/

	public boolean isDbConnected() {
		try {
			return !conection.isClosed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public String isLogin(String user, String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM manager_login WHERE username = ? AND password = ?";
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString(1);
			} 

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			preparedStatement.close();
			resultSet.close();
		}

		return "";
	}


	@Test
	public void checkIfUserHasBeenAddedToTheDatabase() {
			try {
				assertEquals(true, (("a") == isLogin("a","a")));
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}


	@Test
	public void checkIfUserDoesntExsistInTheDatabase() {
			try {
				assertEquals(false, (("null") == isLogin("null","null")));
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}


}














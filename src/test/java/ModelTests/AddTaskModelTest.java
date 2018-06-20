import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.model.AddTaskModel;
import main.java.model.EditTaskModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class AddTaskModelTest{

	Connection conection;
	AddTaskModel addTaskModel = new AddTaskModel();
	EditTaskModel editTaskModel = new EditTaskModel();

	public void addTask(String nameOfTask, String priority, String namesOfEmployees, Date startDate, Date endDate, int effortEstimate) throws SQLException{
		addTaskModel.postTaskInDatabase(nameOfTask,priority,namesOfEmployees,startDate,endDate,effortEstimate);
	}


	@Test
	public void checkIfTaskExistsInTheDatabaseShouldYieldTrueTest1() throws SQLException {
		Date startingDate = java.sql.Date.valueOf("2017-12-15");
		Date endingDate = java.sql.Date.valueOf("2017-12-25");
		addTask("Task 3","Low","John",startingDate,endingDate,7);
		try {
			assertEquals(true,doesExist("Task 3","Low","John",startingDate,endingDate, 7));
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		editTaskModel.deleteTask("Task 3");
	}

	@Test
	public void checkIfTaskExistsInTheDatabaseShouldYieldTrueTest2() throws SQLException {
		Date startingDate = java.sql.Date.valueOf("2018-04-20");
		Date endingDate = java.sql.Date.valueOf("2018-07-11");
		addTask("TestNumber2","High","Alan",startingDate,endingDate,6);

		try {
			assertEquals(true,doesExist("TestNumber2","High","Alan",startingDate,endingDate,6));
			editTaskModel.deleteTask("TestNumber2");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	@Test
	public void checkIfTaskExistsInTheDatabaseShouldYieldTrueTest3() throws SQLException {
		Date startingDate = java.sql.Date.valueOf("2017-12-13");
		Date endingDate = java.sql.Date.valueOf("2017-12-22");
		addTask("TestNumber3","High","Alan",startingDate,endingDate,5);
		try {
			assertEquals(true,doesExist("TestNumber3","Low","Claude",startingDate,endingDate,5));
			editTaskModel.deleteTask("TestNumber3");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}


	public boolean doesExist(String nameOfTask, String priority, String namesOfEmployees, Date startDate,
			Date endDate, int effortEstimate) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM tasks WHERE nameOfTask = ? AND priority = ? AND namesOfEmployees = ? AND startDate = ? AND endingDate = ? AND effortEstimate = ?";
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, nameOfTask);
			preparedStatement.setString(2, priority);
			preparedStatement.setString(3, namesOfEmployees);
			preparedStatement.setDate(4, startDate);
			preparedStatement.setDate(5, endDate);
			preparedStatement.setInt(6, effortEstimate);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}


}

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.model.EditTaskModel;
import main.java.model.AddTaskModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class EditTaskModelTest{

	Connection conection;
	AddTaskModel addTaskModel = new AddTaskModel();
	EditTaskModel editTaskModel = new EditTaskModel();
	String newTaskName1 = "newTaskName1";
	String newTaskName2 = "newTaskName2";
	String newTaskName3 = "newTaskName3";
	String oldTaskName1 = "oldTaskName1";
	String oldTaskName2 = "oldTaskName2";
	String oldTaskName3 = "oldTaskName3";
	
	public void addTask(String nameOfTask, String priority, String namesOfEmployees, Date startDate, Date endDate, int effortEstimate) throws SQLException{
		addTaskModel.postTaskInDatabase(nameOfTask,priority,namesOfEmployees,startDate,endDate,effortEstimate);
	}

	
	public void updateTask(String nameOfTask, String priority, String namesOfEmployees, Date startDate, Date endDate, String effortEstimate, String oldTaskName) throws SQLException{
		EditTaskModel editTaskModel = new EditTaskModel();
		editTaskModel.updateTask(nameOfTask,priority,namesOfEmployees,startDate,endDate,effortEstimate,oldTaskName);
	}
 
    
    @Test
	public void checkIfTaskExistsInTheDatabaseShouldReturnTrueTest1() throws SQLException{
		Date startingDate1 = java.sql.Date.valueOf("2018-04-17");
		Date endingDate1 = java.sql.Date.valueOf("2020-06-04");
		Date startingDate2 = java.sql.Date.valueOf("2019-02-15");
		Date endingDate2 = java.sql.Date.valueOf("2019-04-29");

		addTask(oldTaskName1,"Low","John, Tom",startingDate1, endingDate1, 2);
		updateTask(newTaskName1,"High","John, Tom",startingDate2, endingDate2, "10", oldTaskName1);

		try {
			assertEquals(true, doesExist(newTaskName1,"High","John, Tom",startingDate2, endingDate2, 10) );
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		editTaskModel.deleteTask(newTaskName1);
	}


	@Test
	public void checkIfTaskExistsInTheDatabaseShouldReturnTrueTest2() throws SQLException{
		Date startingDate1 = java.sql.Date.valueOf("2015-12-12");
		Date endingDate1 = java.sql.Date.valueOf("2016-04-03");
		Date startingDate2 = java.sql.Date.valueOf("2017-05-22");
		Date endingDate2 = java.sql.Date.valueOf("2018-11-22");

		addTask(oldTaskName2,"Normal","Peter, Alice",startingDate1, endingDate1, 2);
		updateTask(newTaskName2,"Low","Alistair",startingDate2, endingDate2, "4", oldTaskName2);

		try {
			assertEquals(true, doesExist(newTaskName2,"High","John, Tom",startingDate2, endingDate2, 10) );
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		editTaskModel.deleteTask(newTaskName2);	
	}

	@Test
	public void checkIfTaskExistsInTheDatabaseShouldReturnTrueTest3() throws SQLException{
		Date startingDate1 = java.sql.Date.valueOf("2018-11-12");
		Date endingDate1 = java.sql.Date.valueOf("2019-04-12");
		Date startingDate2 = java.sql.Date.valueOf("2018-03-22");
		Date endingDate2 = java.sql.Date.valueOf("2019-11-22");

		addTask(oldTaskName3,"High","Test, Alice",startingDate1, endingDate1, 4);
		updateTask(newTaskName3,"Normal","Kate",startingDate2, endingDate2, "9", oldTaskName3);

		try {
			assertEquals(true, doesExist(newTaskName3,"Normal","Kate",startingDate2, endingDate2, 10) );
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		editTaskModel.deleteTask(newTaskName3);	
	}

	@Test
	public void deleteTaskFromDatabaseAndCheckIfItsInTheDatabaseYieldsFalse() throws SQLException {
		Date startingDate = java.sql.Date.valueOf("2016-12-19");
		Date endingDate = java.sql.Date.valueOf("2015-04-23");

		addTask("Deleting Test","High","Sugar",startingDate, endingDate, 7);

		editTaskModel.deleteTask("Deleting Test");	
		try {
			assertEquals(false,doesExist("Deleting Test","High","Sugar",startingDate, endingDate, 7));
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	

	@Test
	public void deleteTaskFromDatabaseAndCheckIfItsInTheDatabaseYieldsFalseTest2() throws SQLException {
		Date startingDate = java.sql.Date.valueOf("2018-01-01");
		Date endingDate = java.sql.Date.valueOf("2018-01-01");

		addTask("Deleting Test Number 2","Low","Donald",startingDate, endingDate, 10);

		editTaskModel.deleteTask("Deleting Test Number 2");	

		try {
			assertEquals(false,doesExist("Deleting Test","High","Sugar",startingDate, endingDate, 10));	
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
   
  
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.model.POJOTask;
import main.java.model.TasksManagementModel;
import main.java.model.AddTaskModel;
import java.sql.SQLException;
import java.sql.Date;


public class TasksManagmentModelTest{

		Date startingDate = java.sql.Date.valueOf("2018-01-15");
		Date endingDate = java.sql.Date.valueOf("2018-03-30");
		TasksManagementModel tasksManagementModel = new TasksManagementModel();

	@Test
	public void addTaskToTheDatabase() throws SQLException{
		AddTaskModel addTaskModel = new AddTaskModel();
		addTaskModel.postTaskInDatabase("MangagementTest","High","TestName",startingDate,endingDate,6);
	}
		
	@Test
	public void getTaskNameShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getTaskName().equals("MangagementTest") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getTaskNameShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getTaskName().equals("False1111") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getTaskPriorityShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getTaskPriority().equals("High") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getTaskPriorityShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getTaskPriority().equals("Normal") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getNameOfEmployeesShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getNameOfEmployees().equals("TestName") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getNameOfEmployeesShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getNameOfEmployees().equals("FakeN") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStartingDateShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getStartingDate().equals(startingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStartingDateShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getStartingDate().equals(endingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}



	@Test
	public void getEndingDateShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getEndingDate().equals(endingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getEndingDateShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getEndingDate().equals(startingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStageShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getStage() ==1 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStageShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getStage() == 123123 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}


	@Test
	public void getEffortEstimateShouldNotReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.getTaskDetails("MangagementTest").getEffortEstimate() ==6 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	@Test
	public void getEffortEstimateShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (tasksManagementModel.getTaskDetails("MangagementTest").getEffortEstimate() == 123123 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}


	@Test
	public void getPopulateListViewShouldNotReturnTrue() throws SQLException{

		try {
			assertEquals(true, (tasksManagementModel.populateListView(1) !=null ));
		}catch (Exception e) {
		
			System.out.println(e.getMessage());
		}	

	}
   }
 
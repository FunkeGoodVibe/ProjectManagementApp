import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.model.POJOTask;
import main.java.model.EditTaskModel;
import java.sql.SQLException;
import java.sql.Date;


public class POJOTaskTest{

	Date startingDate = java.sql.Date.valueOf("2017-12-15");
	Date endingDate = java.sql.Date.valueOf("2017-12-25");
	POJOTask pOJOTask = new POJOTask("TestTask","High","Alice",startingDate,endingDate,1,10);
	EditTaskModel editTaskModel = new EditTaskModel();

	@Test
	public void getTaskNameShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (pOJOTask.getTaskName().equals("TestTask") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getTaskNameShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (pOJOTask.getTaskName().equals("False1111") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getTaskPriorityShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (pOJOTask.getTaskPriority().equals("High") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getTaskPriorityShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (pOJOTask.getTaskPriority().equals("Normal") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getNameOfEmployeesShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (pOJOTask.getNameOfEmployees().equals("Alice") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getNameOfEmployeesShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (pOJOTask.getNameOfEmployees().equals("FakeN") ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStartingDateShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (pOJOTask.getStartingDate().equals(startingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStartingDateShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (pOJOTask.getStartingDate().equals(endingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}



	@Test
	public void getEndingDateShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (pOJOTask.getEndingDate().equals(endingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getEndingDateShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (pOJOTask.getEndingDate().equals(startingDate) ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStageShouldReturnTrue() throws SQLException{

		try {
			assertEquals(true, (pOJOTask.getStage() ==1 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStageShouldReturnFalse() throws SQLException{

		try {
			assertEquals(false, (pOJOTask.getStage() == 123123 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStageShouldReturnTrueUsingSetters() throws SQLException{
		pOJOTask.setStage(2);
		try {
			assertEquals(true, (pOJOTask.getStage() ==2 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getStageShouldReturnFalseUsingSetters() throws SQLException{
		pOJOTask.setStage(2);
		try {
			assertEquals(false, (pOJOTask.getStage() == 123123 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	


	}

	@Test
	public void getEffortEstimateShouldReturnTrue() throws SQLException{
		try {
			assertEquals(true, (pOJOTask.getEffortEstimate() == 10 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	

	}

	@Test
	public void getEffortEstimateShouldReturnFalse() throws SQLException{
		try {
			assertEquals(false, (pOJOTask.getEffortEstimate() == 333 ));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	


	}



	@Test
	public void deleteTask() throws SQLException{
		editTaskModel.deleteTask("TestTask");

	}

	



}
   
  
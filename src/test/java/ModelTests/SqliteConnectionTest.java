import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.model.SqliteConnection;
import java.sql.Connection;


public class SqliteConnectionTest{	

	@Test
	public void sqliteConnectionReturnsTrue() {
		
		try {
			assertEquals(true, (SqliteConnection.Connector() !=null) );
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

}
   
  
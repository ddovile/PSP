package module;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestsForModule {
	static Main m;
	private static String path;
	
	@BeforeAll
	static void setUpBeforeClass()
	{
		m = new Main();
		String name = UUID.randomUUID().toString().substring(0,10);
		path = "D:\\Users\\User\\Desktop\\" + name + ".txt";
		
	}
	
	@AfterAll
	static void deleteAfterClass()
	{
		File f = new File(path);
		try {
			Files.delete(f.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddUser_Succeeds() {
		User u = new User("", "TestName", "TestSurname", "865099754" , "email@gmail.com", "Test street 12", "TestTest123/*-+");
		int result = m.addUser(u, path);
		
		assertEquals(1, result);
	}

	@Test
	void testUpdateUser_Succeeds() {
    	String Id = UUID.randomUUID().toString();
		User u = new User(Id, "TestName", "TestSurname", "865099754" , "email@gmail.com", "Test street 12", "TestTest123/*-+");
		int result = m.addUser(u, path);
		
		User updatedUser = u;
		updatedUser.name="NewName";
		result = m.updateUser(updatedUser, path); //Update existing user
		assertEquals(1, result);	
	}
	
	@Test
	void testUpdateUser_Fails() {
    	String Id = UUID.randomUUID().toString();
		User u = new User(Id, "TestName", "TestSurname", "865099754" , "email@gmail.com", "Test street 12", "TestTest123/*-+");
		int result = m.addUser(u, path);
		assertEquals(1, result);
		
		User updatedUser = u;
		updatedUser.id = "-1";
		
		result = m.updateUser(updatedUser, path); //There is no user with ID = -1
		assertEquals(-1, result);
	}

	@Test
	void testDeleteUser_Succeeds() 
	{
    	String Id = UUID.randomUUID().toString();
		User u = new User(Id, "TestName", "TestSurname", "865099754" , "email@gmail.com", "Test street 12", "TestTest123/*-+");
		int result = m.addUser(u, path);
		
		result = m.deleteUser(u.id, path); //Delete existing user
		assertEquals(1, result);
	}
	
	@Test
	void testDeleteUser_Fails() {		
		int result = m.deleteUser("-1", path); //There is no user with ID = -1
		assertEquals(-1, result);
	}

}

package db;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DBConnection;

public class DBConnectionTest {
	
	DBConnection con = null;
	
	@Before
    public void setUp() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File("database/tweets.db"));
		writer.print("");
		writer.close();
		this.con = new DBConnection();
    	this.con.connect();
    }
 
    @After
    public void tearDown() throws FileNotFoundException {
    	
    }	
    
    @Test
    public void testConnection() throws SQLException {
    	boolean exists = con.checkForTableExists("tweets");
    	assertFalse(exists);
    	this.con.createNewTable();
    	exists = con.checkForTableExists("tweets");
    	assertTrue(exists);
    	exists = con.checkForTableExists("testfalse");
    	assertFalse(exists);
    	
    }
   
}

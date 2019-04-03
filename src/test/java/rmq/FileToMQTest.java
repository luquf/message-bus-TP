package rmq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileToMQTest {
	@Before
    public void setUp() {
        
    }
 
    @After
    public void tearDown() {	
    }
    
    @Test
    public void testRMQ() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
    	PrintWriter writer = new PrintWriter("res/new/input/test.txt", "UTF-8");
    	writer.println("title: test");
    	writer.println("author: test");
    	writer.println("date: 2006-02-01");
    	writer.println("content: test");
    	writer.close();
    	TimeUnit.SECONDS.sleep(2);
    	File f = new File("res/new/accepted/test.json");
    	assertTrue(f.exists());
    	f.delete();
    }
 
}

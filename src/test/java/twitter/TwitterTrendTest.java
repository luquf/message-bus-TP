package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import twitter4j.TwitterException;
import twittor.TTrends;

public class TwitterTrendTest {
	@Before
    public void setUp() {
        
    }
 
    @After
    public void tearDown() {
    	
    }
    
    @Test
    public void testGetTrends() throws TwitterException {
    	TTrends ttrends = new TTrends();
    	ArrayList<String> trends = ttrends.getTrends();
    	assertNotEquals(trends.size(), 0);
    	assertEquals(trends.size(), 10);
    	
    }
 
}

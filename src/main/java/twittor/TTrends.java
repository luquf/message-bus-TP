package twittor;

import java.util.ArrayList;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TTrends {
	
	public ArrayList<String> getTrends() throws TwitterException{
		ConfigurationBuilder cb = this.getConf();
		
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Trends trends = twitter.getPlaceTrends(1);
        
        ArrayList<String> list_trends = new ArrayList<String>();
        int count = 0;
        for (Trend trend : trends.getTrends()) {
            if (count < 10) {
            	list_trends.add(trend.getName());
                count++;
            }
        }
		return list_trends;
	}
	
	private ConfigurationBuilder getConf(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		/*
		 * PLEASE READ THIS
		 * This is my private API keys, please do not use those in any context other than this practical work.
		 * Those key will be disable 2 weeks after the limit date.
		 */
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("3jmA1BqasLHfItBXj3KnAIGFB")
                .setOAuthConsumerSecret("imyEeVTctFZuK62QHmL1I0AUAMudg5HKJDfkx0oR7oFbFinbvA")
                .setOAuthAccessToken("265857263-pF1DRxgIcxUbxEEFtLwLODPzD3aMl6d4zOKlMnme")
                .setOAuthAccessTokenSecret("uUFoOOGeNJfOYD3atlcmPtaxxniXxQzAU4ESJLopA1lbC");
        return cb;
	}
}

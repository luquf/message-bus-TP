package twittor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import database.DBConnection;
import twitter4j.TwitterException;

public class TwittorRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Each 10 mn pull twitter API to get the 10 trends on twitter worldwide;
		 */
		   from("timer://mytimer?period=600000") // set to 600000 
		    .process(new Processor() {
		        public void process(Exchange msg) throws TwitterException, SQLException {
		            TTrends trends = new TTrends();
		            msg.getIn().setBody(trends.getTrends().toString());
		            DBConnection db = new DBConnection();
		            db.connect();
		            boolean exists = db.checkForTableExists("tweets");
		            if (!exists) {
		            	db.createNewTable();
		            }
		            for (String trend : trends.getTrends()) {
		            	log.info("Tendance du moment : {}", trend);
		            	String pattern = "EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ";
		            	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("da", "DK"));
		            	String date = simpleDateFormat.format(new Date());
		            	db.insertTrend(date, trend);
		 
		            }
		            db.close();
		        }
		    });;
	}

}

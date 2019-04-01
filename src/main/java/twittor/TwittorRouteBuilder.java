package twittor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import twitter4j.TwitterException;

public class TwittorRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Each 10 mn pull twitter API to get the 10 trends on twitter worldwide;
		 */
		   from("timer://mytimer?period=600000")
		    .process(new Processor() {
		        public void process(Exchange msg) throws TwitterException {
		            TTrends trends = new TTrends();
		            msg.getIn().setBody(trends.getTrends().toString());
		            for (String trend : trends.getTrends()) {
		            	log.info("Tendance du moment : {}", trend);
		            }
		            
		        }
		    });;
	}

}

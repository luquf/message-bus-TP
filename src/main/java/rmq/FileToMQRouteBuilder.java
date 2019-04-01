package rmq;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;


public class FileToMQRouteBuilder extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		/*
		 * Pooling from res/new folder each second
		 */
		
		from("rabbitmq://localhost:5672/exchangefile?username=guest&password=guest&queue=mqpending")
		.process(new Processor() {
            public void process(Exchange msg) throws Exception {
            	String json = msg.getIn().getBody(String.class);
            	JSONObject article_json = new JSONObject(json);
            	article_json.put("submission_date", DateTimeFormatter.ofPattern("yyy/MM/dd").format(LocalDate.now()));
            	article_json.put("accepted_date", DateTimeFormatter.ofPattern("yyy/MM/dd").format(LocalDate.now()));
            	article_json.put("reviewer_name", "tbuatoislberton");
            	msg.getIn().setBody(article_json.toString());
            }
            	
        })
        .to("file://res/new/accepted?fileName=${file:name.noext}.json");
		
		from("file://res/new/input?delay=1000")
        .process(new Processor() {
            public void process(Exchange msg) throws Exception {
            	String file = msg.getIn().getBody(String.class);
            	String[] lines = file.split(System.getProperty("line.separator"));
            	JSONObject article_json = new JSONObject();
            	article_json.put("title", lines[0].replaceFirst("title: ", "").replaceAll("\"", ""));
            	article_json.put("author", lines[1].replaceFirst("author: ", ""));
            	article_json.put("date", lines[2].replaceFirst("date: ", ""));
            	article_json.put("content", lines[3].replaceFirst("content: ", ""));
            	msg.getIn().setBody(article_json.toString());
            }
            	
        }).to("rabbitmq://localhost:5672/exchangefile?username=guest&password=guest&queue=mqpending");
	}
}

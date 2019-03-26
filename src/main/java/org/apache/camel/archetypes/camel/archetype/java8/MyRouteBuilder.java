package org.apache.camel.archetypes.camel.archetype.java8;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * A Camel Java8 DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {
    private static final Object[] OBJECTS = new Object[]{
        "A string",
        new Integer(1),
        new Double(1.0)
    };

    private int index;
    /**
     * Let's configure the Camel routing rules using Java code...
     * @throws TimeoutException 
     * @throws IOException 
     */
    public void configure() throws IOException, TimeoutException {

    	ConnectionFactory connectionFactory = new ConnectionFactory();
    	connectionFactory.setHost("localhost");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("test", false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", "test", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }

    	// Note we can explicity name the component
    }

    private Object randomBody(Message m) {
        return OBJECTS[m.getHeader("index", Integer.class)];
    }
}

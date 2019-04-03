package org.apache.camel.archetypes.camel.archetype.java8;

import org.apache.camel.main.Main;

import rmq.FileToMQRouteBuilder;
import twittor.TwittorRouteBuilder;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        //main.addRouteBuilder(new MyRouteBuilder());
        main.addRouteBuilder(new TwittorRouteBuilder());
        //main.addRouteBuilder(new FileToMQRouteBuilder());
        main.run(args);
    }
}


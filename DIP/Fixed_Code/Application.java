package DIP.Fixed_Code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Dependency Inversion Principle (DIP)

/**
 * Key Benefits of DIP
 * Decoupling: Switch class doesn't know about specific devices
 * Extensibility: Add new devices without modifying Switch class
 * Testability: Easy to mock devices for testing
 * Flexibility: Can switch devices at runtime
 */


public class Application {
    List<Logger> loggers;
    Application(List<Logger> loggers){
        this.loggers = loggers;
    }

    public void userProcess(String username){
        for (Logger logger : loggers) {
            logger.log("User processed: "+username);
        }
    }

    public static void main(String[] args) {

        // Configure logging strategy externally
        List<Logger> productionLoggers = Arrays.asList(
                new FileLog(),
                new DBLog(),
                new ConsoleLog()
        );

        List<Logger> developmentLoggers = Arrays.asList(
                new ConsoleLog(),
                new FileLog()
        );

        List<Logger> alertLoggers = Arrays.asList(
                new FileLog()
        );

        // Same Application class, different logging behaviors
        Application productionApp = new Application(productionLoggers);
        Application devApp = new Application(developmentLoggers);
        Application alertApp = new Application(alertLoggers);

        productionApp.userProcess("production_user");
        devApp.userProcess("dev_user");
        alertApp.userProcess("alert_user");

        // Testing is easy - use mock loggers
        List<Logger> testLoggers = Arrays.asList(new ConsoleLog());
        Application testApp = new Application(testLoggers);
        testApp.userProcess("test_user");

    }
}

interface Logger{
    public void log(String message);
}

class FileLog implements Logger{
    public void log(String message){
        System.out.println("Writing to File : "+message);
    }
}

class DBLog implements Logger{
    public void log(String message){
        System.out.println("Writing to Database : "+message);
    }
}

class ConsoleLog implements Logger{
    public void log(String message){
        System.out.println("Writing to Console : "+message);
    }
}

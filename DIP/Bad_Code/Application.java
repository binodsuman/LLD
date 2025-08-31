package DIP.Bad_Code;

// High-level module (business logic) - DEPENDS ON LOW-LEVEL MODULES
public class Application {
    FileLogger fileLogger;
    DatabaseLogger databaseLogger;
    ConsoleLogger consoleLogger;

    public Application(FileLogger fileLogger, DatabaseLogger databaseLogger, ConsoleLogger consoleLogger) {
        this.fileLogger = fileLogger;
        this.databaseLogger = databaseLogger;
        this.consoleLogger = consoleLogger;
    }

    public void processUser(String username){
        fileLogger.logToFile("User processed: " + username);
        databaseLogger.logToDatabase("User activity: " + username);
        consoleLogger.logToConsole("User: " + username + " processed successfully");
    }

    public static void main(String[] args) {
        FileLogger fileLogger = new FileLogger();
        DatabaseLogger dbLogger = new DatabaseLogger();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        Application app = new Application(fileLogger, dbLogger, consoleLogger);
        app.processUser("Binod Suman");

        // Issues:
        // 1. Cannot easily switch logging methods
        // 2. Hard to test (real file/database operations)
        // 3. Adding new logger requires modifying Application class
    }
}


// Low-level modules (implementation details)
class FileLogger {
    public void logToFile(String message) {
        System.out.println("Writing to file: " + message);
        // Actual file writing logic would be here
    }
}

class DatabaseLogger {
    public void logToDatabase(String message) {
        System.out.println("Saving to database: " + message);
        // Actual database insert logic would be here
    }
}

class ConsoleLogger {
    public void logToConsole(String message) {
        System.out.println("Console output: " + message);
    }
}

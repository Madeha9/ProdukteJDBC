import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class ProductImporterApp {
    public static void main(String[] args) {

        ConfigurationManager configManager = new ConfigurationManager("src/main/resources/app.properties");
        AppConfig config = configManager.load(args);
        System.out.println("DB URL = " + config.dbUrl);
        System.out.println("DB USER = " + config.dbUser);
        System.out.println("DB PASSWORD = " + config.dbPassword);
        // Beispiel: Modus steuert, welcher Service aufgerufen wird
        AppService service;
        if ("import".equalsIgnoreCase(config.mode)) {
            service = new ImportService();
        } else { // default: list
            service = new ListService();
        }
        service.execute(config);
    }
}






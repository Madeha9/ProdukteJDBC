package ProductImportClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MakeDbConnection {
    //Logger definieren , anstatt System out verwenden
    private static final Logger logger = Logger.getLogger(MakeDbConnection.class.getName());

    public static Connection getConnection(AppConfig config) throws SQLException {
        if (config.dbUrl == null || config.dbUser == null || config.dbPassword == null) {
            logger.severe("Datenbank-Konfiguration fehlt!");
            throw new RuntimeException("DB-Konfiguration fehlt!" + " Bitte DB_URL, DB_USER und DB_PASSWORD prüfen.");
        }
        logger.info("Verbindung zur Datenbank wird aufgebaut...");

        Connection conn = DriverManager.getConnection(config.dbUrl, config.dbUser, config.dbPassword);
        logger.info("Verbindung erfolgreich!");
        return conn;
    }
}



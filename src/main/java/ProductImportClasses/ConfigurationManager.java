package ProductImportClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationManager {

    private final String propertiesFilePath;

    public ConfigurationManager(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    // Properties laden
    private Map<String, String> loadProperties() {
        Map<String, String> map = new HashMap<>();
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            props.load(fis);
            for (String key : props.stringPropertyNames()) {
                map.put(key, props.getProperty(key));
//                System.out.println("Properties Map: " + map);
            }
        } catch (IOException e) {
            System.out.println("❌ Fehler beim Laden der Properties: " + e.getMessage());
        }

        return map;
    }

    // Umgebungsvariablen laden
    private Map<String, String> loadEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("DB_URL", System.getenv("DB_URL"));
        env.put("DB_USER", System.getenv("DB_USER"));
        env.put("DB_PASSWORD", System.getenv("DB_PASSWORD"));
        return env;
    }

    // Kommandozeilenparameter parsen
    public Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();

        for (String arg : args) {
            if (arg.startsWith("--input=")) {
                map.put("inputFile", arg.substring("--input=".length()));
            } else if (arg.startsWith("--mode=")) {
                map.put("mode", arg.substring("--mode=".length()));
            } else if (arg.startsWith("--only-active=")) {
                map.put("onlyActive", arg.substring("--only-active=".length()));
            } else if (arg.startsWith("--min-price=")) {
                map.put("minPrice", arg.substring("--min-price=".length()));
            }
        }

        return map;
    }

    // Alles zusammenführen: CLI > Env > Properties > Defaults
    public AppConfig load(String[] args) {
        Map<String, String> props = loadProperties();
        Map<String, String> env = loadEnv();
        Map<String, String> cli = parseArgs(args);

        // Datenbank-Konfiguration
        String dbUrl = cli.getOrDefault("DB_URL", env.getOrDefault("DB_URL", props.get("DB_URL")));
        String dbUser = cli.getOrDefault("DB_USER", env.getOrDefault("DB_USER", props.get("DB_USER")));
        String dbPassword = cli.getOrDefault("DB_PASSWORD", env.getOrDefault("DB_PASSWORD", props.get("DB_PASSWORD")));

        // Sonstige Einstellungen
        String inputFile = cli.getOrDefault("inputFile", props.getOrDefault("inputFile", "/products.json"));
        String mode = cli.getOrDefault("mode", props.getOrDefault("mode", "list"));
        boolean onlyActive = Boolean.parseBoolean(cli.getOrDefault("onlyActive", "false"));
        BigDecimal minPrice = new BigDecimal(cli.getOrDefault("minPrice", "0"));

        return new AppConfig(inputFile, mode, dbUrl, dbUser, dbPassword, onlyActive, minPrice);
    }
}

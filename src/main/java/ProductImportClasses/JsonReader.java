package ProductImportClasses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//JSON-Dateien einlesen und in ProductImportClasses.Product-Objekte umwandeln
public class JsonReader {
    //Logger, um Fehlermeldungen oder Infos auszugeben , nur für diese klasse und final
    private static final Logger logger = Logger.getLogger(JsonReader.class.getName());

    //Json datei einlesen , und liste zurückgeben
    public static List<Product> readProducts(String resourcePath) throws IOException {
        //ObjectMapper wandelt JSON und  Java-Objekte und umgekehrt
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonReader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                logger.severe("JSON file not found: " + resourcePath);
                return List.of();   // empty list damit der programm nicht stopp
            }
            return mapper.readValue(is, new TypeReference<List<Product>>() {
            });
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to read JSON file: " + resourcePath, e);
            return List.of();
        }
    }
}

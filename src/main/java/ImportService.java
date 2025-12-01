import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ImportService implements AppService {

    private final ProductRepository productRepository;

    public ImportService() {
        this.productRepository = new ProductRepository();
    }

    @Override
    public void execute(AppConfig config) {
        try (Connection conn = MakeDbConnection.getConnection(config)) {

            // JSON-Datei einlesen
            List<Product> products = JsonReader.readProducts(config.inputFile);

            // Produkte importieren/Upsert
            productRepository.insertAll(products, conn);

            System.out.println("✔ Produkte erfolgreich importiert!");

        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Importieren: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Fehler beim Einlesen der JSON-Datei: " + e.getMessage());
        }
    }
}

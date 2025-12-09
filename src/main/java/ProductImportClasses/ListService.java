package ProductImportClasses;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListService implements AppService {

    private final ProductRepository productRepository;

    public ListService() {
        this.productRepository = new ProductRepository();
    }

    @Override
    public void execute(AppConfig config) {
        try (Connection conn = MakeDbConnection.getConnection(config)) {

            List<Product> products;

            // CLI-Filter anwenden
            if (config.onlyActive && config.minPrice.compareTo(BigDecimal.ZERO) > 0) {
                products = productRepository.findActiveByMinPrice(conn, config.minPrice);
            } else if (config.onlyActive) {
                products = productRepository.findActive(conn);
            } else if (config.minPrice.compareTo(BigDecimal.ZERO) > 0) {
                products = productRepository.findByMinPrice(conn, config.minPrice);
            } else {
                products = productRepository.findAll(conn);
            }

            // Ausgabe formatiert
            ProductFormatter.formatList(products);

        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Abrufen der Produkte: " + e.getMessage());
        }
    }
}

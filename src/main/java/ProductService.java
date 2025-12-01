import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
//        this.listService = new ListService(); // nutzt intern ProductRepository
    }

    private AppConfig config;
    Connection conn;

    {
        try {
            conn = MakeDbConnection.getConnection(config);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Alle Produkte laden
    public List<Product> getAllProducts() {
        // Wir müssen ListService etwas anpassen, damit es eine Liste liefert

        try {
            return productRepository.findAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Nur aktive Produkte
    public List<Product> getActiveProducts() {
        try {
            return productRepository.findActive(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Filter mit minPrice
    public List<Product> filterProducts(boolean onlyActive, BigDecimal minPrice) {
        try {
            return productRepository.findActiveByMinPrice(conn, minPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package Fx_GUI_Classe;

import ProductImportClasses.AppConfig;
import ProductImportClasses.MakeDbConnection;
import ProductImportClasses.Product;
import ProductImportClasses.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//to take care of the db connection and Json files
public class ProductService {
    private final ProductRepository productRepository = null;

    public ProductService() {
//        this.productRepository = productRepository;
//        this.listService = new ProductImportClasses.ListService(); // nutzt intern ProductImportClasses.ProductRepository
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
        // Wir müssen ProductImportClasses.ListService etwas anpassen, damit es eine Liste liefert

        try {
            return productRepository.findAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Nur aktive Produkte
//    public List<Product> getActiveProducts() {
//        try {
//            return productRepository.findActive(conn);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // Filter mit minPrice
//    public List<Product> filterProducts(boolean onlyActive, BigDecimal minPrice) {
//        try {
//            return productRepository.findActiveByMinPrice(conn, minPrice);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

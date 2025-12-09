package Fx_GUI_Classe;

import ProductImportClasses.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;
    private final AppConfig config;
    private final Connection conn;

    public ProductService(String[] args) {
        this.productRepository = new ProductRepository();
        ConfigurationManager configManager = new ConfigurationManager("app.properties");
        this.config = configManager.load(args);

        try {
            this.conn = MakeDbConnection.getConnection(config);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void importjson(String inputfilepath) {
        Runnable importer = () -> {
            ImportService service = new ImportService();
            service.execute(config);
        };
        Thread loadfile = new Thread(importer);
        loadfile.start();
    }
}

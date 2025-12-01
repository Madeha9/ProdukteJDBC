import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MainController {

    @FXML
    private TableView<Product> tableProducts;
    @FXML
    private TableColumn<ProductModel, Long> colId;
    @FXML
    private TableColumn<ProductModel, String> colName;
    @FXML
    private TableColumn<ProductModel, String> colCategory;
    @FXML
    private TableColumn<ProductModel, Boolean> colActive;

    private MakeDbConnection conn;
    private ProductService productService;

    // Setter Injection
    public void setServices(MakeDbConnection conn, ProductService productService) {
        this.conn = conn;
        this.productService = productService;
    }

    @FXML
    public void initialize() {
        // TableView Spalten verbinden
        colId.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        colName.setCellValueFactory(c -> c.getValue().nameProperty());
        colCategory.setCellValueFactory(c -> c.getValue().categoryProperty());
        colActive.setCellValueFactory(c -> c.getValue().activeProperty().asObject());

        // Standardmäßig alle Produkte laden
        onViewAll();
    }

    @FXML
    private void onViewAll() {
        List<Product> product = productService.getAllProducts();
        updateTable(product);
    }

    @FXML
    private void onViewActive() {
        List<Product> activeProducts = productService.getActiveProducts();
        updateTable(activeProducts);
    }

    private void updateTable(List<Product> products) {
        ObservableList<Product> list = FXCollections.observableArrayList(products);
        tableProducts.setItems(list);
    }
}

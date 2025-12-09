package Fx_GUI_Classe;

import ProductImportClasses.MakeDbConnection;
import ProductImportClasses.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class MainController {

    @FXML
    TableColumn<ProductModel, BigDecimal> colPrice;
    @FXML
    private TableColumn<ProductModel, Long> colId;
    @FXML
    private TableColumn<ProductModel, String> colName;
    @FXML
    private TableColumn<ProductModel, String> colCategory;
    @FXML
    private TableColumn<ProductModel, Boolean> colActive;
    @FXML
    //Produckte Tabelle erstellen
    //die Tabelle hat Produkt objekte  von der klasse objelte //Generic dattype
    private TableView<Product> tableProducts;
    //    @FXML
    private Button btnAddProduct;
    @FXML
    private Button insertAll;
    @FXML
    private Button importfile;
    @FXML
    private Button findAll;
    @FXML
    private Button btnFindActive;

    private MakeDbConnection conn;
    private ProductService productService = new ProductService();

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
        colPrice.setCellValueFactory(c -> c.getValue().priceProperty());

        // Standardmäßig alle Produkte laden
        findAll.setDisable(false);
    }

    @FXML
    private void findAllAction(ActionEvent event) throws SQLException {
        List<Product> product = productService.getAllProducts();
        updateTable(product);
    }
//    @FXML
//    private void onViewActive() {
//        List<Product> activeProducts = productService.getActiveProducts();
//        updateTable(activeProducts);
//    }

    private void updateTable(List<Product> products) {
        ObservableList<Product> list = FXCollections.observableArrayList(products);
        tableProducts.setItems(list);
    }
}

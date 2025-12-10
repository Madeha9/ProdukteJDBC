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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class MainController {
    @FXML
    TableColumn<ProductModel, BigDecimal> colPrice;
    ProductService productService;
    @FXML
    private TableColumn<ProductModel, Long> colId;
    @FXML
    private TableColumn<ProductModel, String> colName;
    @FXML
    private TableColumn<ProductModel, String> colCategory;
    @FXML
    private TableColumn<ProductModel, Boolean> colActive;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button insertAll;
    @FXML
    private Button importfile;
    @FXML
    private Button findAll;
    @FXML
    private Button chooseFile;
    @FXML
    private Button btnFindActive;
    private MakeDbConnection conn;
    @FXML
    private TableView<ProductModel> tableProducts;
    //Produckte Tabelle erstellen
    //die Tabelle hat Produkt objekte  von der klasse objelte //Generic dattype
    @FXML
    private TextField textfilePath;

    //so das, der Benuzer kann ein Datei auswahlen
    @FXML
    private void choosefile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Json File");
        //nur json Datei kann importiert werden , der Benuzer sieht nur .json datei zu wahlen
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter
                ("JSON file", "*.json"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            textfilePath.setText(file.getAbsolutePath());
        }

    }

    @FXML
    public void initialize() {
        // TableView Spalten verbinden
        colId.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        colName.setCellValueFactory(c -> c.getValue().nameProperty());
        colCategory.setCellValueFactory(c -> c.getValue().categoryProperty());
        colActive.setCellValueFactory(c -> c.getValue().activeProperty().asObject());
        colPrice.setCellValueFactory(c -> c.getValue().priceProperty());
        productService = new ProductService(new String[0]);

        // Standardmäßig alle Produkte laden
        findAll.setDisable(false);
//        importfile.setDisable(false);
    }

    @FXML
    private void findAllAction(ActionEvent event) throws SQLException {
        List<Product> product = productService.getAllProducts();
        updateTable(product);
    }

    @FXML
    private void importfile(ActionEvent event) throws SQLException {
        String path = textfilePath.getText();
        if (path.isEmpty()) {
            System.out.println("Bitte eine Datei auswahlen");
            return;
        }

        System.out.println("Json Datei importieren" + path);
        productService.importjson(path);
        System.out.println("Json Datei wird hochgeladen");


        //Asynchron starten
    }

    @FXML
    private void onViewActive(ActionEvent event) throws SQLException {
        List<Product> activeProducts = productService.getActiveProducts();
        updateTable(activeProducts);
    }

    private void updateTable(List<Product> products) {
        ObservableList<ProductModel> list = FXCollections.observableArrayList();
        for (Product p : products) {
            list.add(new ProductModel(p));
        }
        tableProducts.setItems(list);
    }

}

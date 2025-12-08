package Fx_GUI_Classe;//Startet JavaFX, lädt die FXML
//Start klasse für GUI

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        //fenster erzeugen
        Scene scene = new Scene(loader.load());
        stage.setTitle("ProductImportClasses.Product Service");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //Java fx starten
        launch(args);
    }
}

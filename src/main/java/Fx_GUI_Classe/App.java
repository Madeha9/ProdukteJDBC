package Fx_GUI_Classe;//Startet JavaFX, lädt die FXML
//Start klasse für GUI

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    static void main(String[] args) {
//        ConfigurationManager configManager = new ConfigurationManager("app.properties");
//        AppConfig config = configManager.load(args);
        //Java fx starten
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        //fenster erzeugen
        Scene scene = new Scene(loader.load());
        stage.setTitle("Product Service");
        stage.setScene(scene);
        stage.show();
    }
}

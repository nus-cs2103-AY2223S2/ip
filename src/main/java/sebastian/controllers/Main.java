package sebastian.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sebastian.Sebastian;



/**
 * Class used to connect the controller and view of MainWindow
 */
public class Main extends Application {
    private Sebastian sebastian = new Sebastian("src/main/resources/SebastianData.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sebastian");
            fxmlLoader.<MainWindow>getController().setSebastian(sebastian);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

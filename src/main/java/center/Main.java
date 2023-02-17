package center;

import java.io.IOException;

import UI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Skylar skylar = new Skylar();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Skylar");
            fxmlLoader.<MainWindow>getController().setSkylar(skylar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

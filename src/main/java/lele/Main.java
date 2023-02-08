package lele;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Credits to @SPWwj for the idea of creating a separate class
 * to handle the GUI.
 */
public class Main extends Application {
    private final Lele lele = new Lele("./data/lele.txt");
    public Main() {}

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLele(lele);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

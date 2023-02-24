package wessy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wessy.javafxnodes.MainWindow;

/**
 * A GUI for Wessy using FXML.
 */
public class Main extends Application {

    private Wessy wessy = new Wessy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(wessy.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("Wessy");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWessy(wessy);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
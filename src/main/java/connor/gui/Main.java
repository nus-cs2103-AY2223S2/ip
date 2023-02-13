package connor.gui;

import java.io.IOException;

import connor.Connor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Connor using FXML.
 */
public class Main extends Application {

    private Connor connor = new Connor();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Connor");
            fxmlLoader.<MainWindow>getController().setConnor(connor);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

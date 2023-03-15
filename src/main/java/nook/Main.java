package nook;

import java.io.IOException;

import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TomNook tomNook = new TomNook();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Nook");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/DaIcon.png")));
            fxmlLoader.<MainWindow>getController().setDuke(tomNook);
            fxmlLoader.<MainWindow>getController().displayGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package luffy;

import java.io.IOException;

import luffy.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Luffy using FXML.
 */
public class Main extends Application {

    private Luffy luffy = new Luffy(Luffy.PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLuffy(luffy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

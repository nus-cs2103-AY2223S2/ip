package berry;

import java.io.IOException;

import berry.storage.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Berry using FXML.
 */
public class Main extends Application {

    private Berry berry = new Berry();

    public Main() throws Storage.InvalidStorageFilePathException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBerry(berry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
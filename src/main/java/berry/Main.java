package berry;

import java.io.IOException;

import berry.storage.Storage;
import javafx.application.Application;
import javafx.application.Platform;
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
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setBerry(berry);
            mainWindow.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
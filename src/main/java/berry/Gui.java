package berry;

import java.io.IOException;

import berry.storage.Storage;
import berry.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Berry using FXML.
 */
public class Gui extends Application {

    private Berry berry = new Berry();

    public Gui() throws Storage.InvalidStorageFilePathException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setBerry(berry);
            mainWindow.setStage(stage);
            stage.setTitle("🍒Berry🍒");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
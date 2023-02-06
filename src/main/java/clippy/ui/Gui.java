package clippy.ui;

import java.io.IOException;

import clippy.Clippy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private Clippy clippy;

    @Override
    public void start(Stage stage) {
        try {
            clippy = new Clippy();
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setClippy(clippy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

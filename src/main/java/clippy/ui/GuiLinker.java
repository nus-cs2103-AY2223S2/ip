package clippy.ui;

import java.io.IOException;

import clippy.Clippy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A 'linker' between the FXML controllers and the main Clippy program.
 */
public class GuiLinker extends Application {
    private Clippy clippy;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiLinker.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow gui = fxmlLoader.<MainWindow>getController();
            clippy = new Clippy(gui);
            gui.setClippy(clippy);
            stage.setTitle("Clippy");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

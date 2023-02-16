package duke;

import java.io.IOException;
import java.util.Objects;

import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main wrapper class to couple Duke and the UI together.
 */
public class Main extends Application {

    private final Duke duke = new Duke("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Ui.fxml"));
            AnchorPane ui = fxmlLoader.load();
            Scene scene = new Scene(ui);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setDuke(this.duke);
            stage.setMinWidth(400.0);
            stage.setMinHeight(600.0);
            scene.getStylesheets().add(
                    Objects.requireNonNull(Ui.class.getResource("/view/style.css")).toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package duke.gui;

import java.io.IOException;

import duke.components.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Gui class to display and handle input and output from Duke.
 */
public class DukeGui extends Application {
    private Duke duke = new Duke(Duke.getFilePath());


    @Override
    public void start(Stage stage) {
        try {
            loadGui(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGui(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DukeGui.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        stage.show();
    }
}

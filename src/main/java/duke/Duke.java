package duke;

import java.io.IOException;

import duke.core.Core;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * <h1>Duke Task Management Application</h1>
 * Duke is an applications that allow the user to manage their task from the command line.
 *
 * @author Stanley Neoh Jia Jun
 */
public class Duke extends Application {
    private Core core;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        core = new Core();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setupCore(core);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();
    }
}

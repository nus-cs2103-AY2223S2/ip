package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Stage.fxml"));
            MainWindow mainWindow = new MainWindow(duke);
            fxmlLoader.setController(mainWindow);
            fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);

            stage.setOnShowing(event -> {
                mainWindow.greetUser();
            });
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

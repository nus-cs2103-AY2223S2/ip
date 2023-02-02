import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DukeMain extends Application {
    private Duke duke = new Duke("./data", "./data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            duke.loadTasksFromFile();

            FXMLLoader fxmlLoader = new FXMLLoader(DukeMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<duke.ui.MainWindow>getController().setDuke(duke);
            stage.setTitle("Duke");

            stage.setOnCloseRequest(x -> duke.saveTasksToFile());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

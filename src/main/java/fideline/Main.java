package fideline;

import java.io.IOException;

import fideline.exception.CorruptedDataFileException;
import fideline.exception.UnableToCreateDataFileException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Fideline fideline;

    @Override
    public void start(Stage stage) throws CorruptedDataFileException, UnableToCreateDataFileException {
        if (fideline == null) {
            fideline = new Fideline("./task-data.txt");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Fideline");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFideline(fideline);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

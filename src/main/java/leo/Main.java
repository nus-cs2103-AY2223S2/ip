package leo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import leo.task.LeoTaskException;

import java.io.IOException;

/**
 * A GUI for Leo using FXML. Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {

    private Leo leo;
    @Override
    public void start(Stage stage) throws LeoTaskException{
        initDuke();
        setStage(stage);
    }

    private void initDuke() throws LeoTaskException {
        leo = new Leo();
    }

    private void setStage(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLeo(leo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

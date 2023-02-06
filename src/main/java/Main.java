import duke.Duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Handles start event
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MainWindow(duke));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles exit event
     */
    @Override
    public void stop() throws Exception {
        duke.close();
    }
}

package duke;

import java.util.Optional;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        AnchorPane ap = new MainWindow(duke, stage);
        Scene scene = new Scene(ap);

        stage.setScene(scene);

        /* Custom stage settings */
        stage.setResizable(false);
        stage.setTitle(duke.getName());
        stage.setOnCloseRequest(event -> {
            event.consume();
            confirmClose(stage);
        });

        stage.show();
    }

    /**
     * Creates a confirmation popup to ask whether user wants to exit.
     * @param stage The stage operating.
     */
    private void confirmClose(Stage stage) {
        /* Create a confirmation alert*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to LeExit?");

        /* Create buttons */
        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        /* Check user's answer */
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == buttonYes) {
            stage.close();
        }
    }
}

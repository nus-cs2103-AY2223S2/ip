package duke.ui;
import duke.Duke;
import duke.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class handles the formatting and outputting of text based UI to the user.
 */


public class UiController {

    private FXMLLoader fxmlLoader;
    private final Duke context;

    public UiController(Duke context) {
        this.context = context;
    }

    /**
     * Method to display standard welcome message to user.
     */
    public void displayWelcomeMessage() {
        String welcomeText = """
                Welcome! My name is Duke.
                How may I help?""";
        fxmlLoader.<MainWindow>getController().displayDukeDialog(welcomeText);
    }

    public void displayGoodbyeMessage() {
        String goodbyeText = " Goodbye. Hope to see you again soon.";
        fxmlLoader.<MainWindow>getController().displayDukeDialog(goodbyeText);
    }

    public void initUiElems(Stage stage) {
        try {
            this.fxmlLoader = new FXMLLoader(UiController.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUiController(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Message getResponse(Message msg) {
        return context.respondToMessage(msg);
    }
}

package duke.ui;
import duke.Duke;
import duke.Message;
import duke.exceptions.DukeException;
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
     * Displays welcome message from Duke on GUI.
     */
    public void displayWelcomeMessage() {
        String welcomeText = """
                Welcome! My name is Duke.
                How may I help?""";
        fxmlLoader.<MainWindow>getController().displayDukeDialog(welcomeText);
    }

    /**
     * Displays goodbye message from Duke on GUI.
     */
    public void displayGoodbyeMessage() {
        String goodbyeText = " Goodbye. Hope to see you again soon.";
        fxmlLoader.<MainWindow>getController().displayDukeDialog(goodbyeText);
    }

    /**
     * Initialises UI elements from FXML files.
     * @param stage <code>Stage</code> supplied to the instance of JavaFX <code>Application</code>.
     */
    public void initUiElems(Stage stage) {
        try {
            this.fxmlLoader = new FXMLLoader(UiController.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUiController(this);
            stage.setTitle("Duke chatbot: your personal task manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets Duke's response to a message from the user.
     * @param msg User's message.
     * @return Duke's response.
     */
    Message getResponse(Message msg) {
        try {
            return context.respondToMessage(msg);
        } catch (DukeException e) {
            return new Message(e.getMessage());
        }
    }
}

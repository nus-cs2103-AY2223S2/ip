package duke.ui;

import duke.Duke;
import duke.tasks.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Manages the user interface of the application.
 *
 * @author Samarth Verma
 */
public class UserInterface extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /** Prints a greeting message. */
    public void showGreeting() {
        showMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    /** Prints an exit message */
    public void showExitMessage() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;

    }

    /**
     * Gets the input from the user.
     *
     * @return The input from the user.
     */
    public String getInput() {
        return "";
    }

    /**
     * Prints the given list of tasks
     *
     * @param list List which is printed
     */
    public void showTasks(TaskList list) {

    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage));

        duke.run(input);
        userInput.clear();
    }
}

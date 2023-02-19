package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.Duke;
import duke.tasks.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    /** Creates a new UserInterface object. */
    public UserInterface() {
    }

    /** Prints a greeting message. */
    public void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Duke", null));
    }

    /** Prints an exit message */
    public void showExitMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Bye. Hope to see you again soon!", null));
    }

    /**
     * Prints a message.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {

    }

    /** Prints the prompt symbol. */
    public void showPrompt() {

    }

    public void setDuke(Duke d) {
        duke = d;
        // dialogContainer.getChildren().addAll(
        // DialogBox.getDukeDialog(duke.getGreeting(), dukeImage));
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
        userInput.clear();
    }
}

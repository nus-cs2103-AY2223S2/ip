package duke.ui;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    /** Scroll pane for the window */
    @FXML
    private ScrollPane scrollPane;

    /** Dialog container for dialog and user's icon */
    @FXML
    private VBox dialogContainer;

    /** Text field for user input */
    @FXML
    private TextField userInput;

    /** Button to send entered command */
    @FXML
    private Button sendButton;

    /** Duke to control app logic */
    private Duke duke;

    /** User's icon */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /** Duke's icon */
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the app
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke used for the app's logic.
     *
     * @param duke Duke used.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Greets the user.
     */
    public void showGreeting() {
        showDukeDialog("Hello!\nRetrieving tasks from storage...");
    }

    /**
     * Informs user that task list is not found on disk.
     */
    public void showStorageLoadFailure() {
        showDukeDialog("Task list not found on disk, creating empty task list");
    }

    /**
     * Informs user that task list on disk is corrupted.
     */
    public void showStorageCorrupted(String message) {
        showDukeDialog(message);
    }
    /**
     * Prompts user to start giving commands.
     */
    public void showPrompt() {
        showDukeDialog("Task list is now ready! Awaiting commands...");
    }

    /**
     * Informs user that the command given is invalid.
     *
     * @param message Message to the user.
     */
    public void showBadCommandMessage(String message) {
        showDukeDialog(message);
    }

    /**
     * Informs user that a given task is successfully added to the task list.
     *
     * @param task Added task.
     */
    public void showAddTaskSuccessMessage(Task task) {
        showDukeDialog("Added task: " + task);
    }

    /**
     * Informs user that a given task is successfully marked as read or unread.
     *
     * @param task Marked task.
     */
    public void showMarkTaskSuccessMessage(Task task) {
        showDukeDialog("Marked task: " + task + " as " + (task.getIsDone() ? "" : "not ") + "done");
    }

    /**
     * Informs user that a given task is successfully deleted from the task list.
     *
     * @param task Deleted task.
     */
    public void showDeleteTaskSuccessMessage(Task task) {
        showDukeDialog("Deleted task: " + task);
    }

    /**
     * Displays the full list of tasks.
     *
     * @param tasks Tasks to display.
     */
    public void showAllTasks(TaskList tasks) {
        showDukeDialog(tasks.toString());
    }

    /**
     * Displays tasks containing a certain keyword
     * @param tasks String representation of filtered tasks.
     */
    public void showTasksWithKeyword(String tasks) {
        showDukeDialog(tasks);
    }

    /**
     * Informs user that an error occurred when attempting to save the task list on disk.
     */
    public void showStorageSaveFailure() {
        showDukeDialog("Error saving tasks to disk!");
    }

    /**
     * Bids farewell to the user.
     */
    public void showFarewellMessage() {
        showDukeDialog("Good bye!\nClosing in 5 seconds...");
    }

    /**
     * Handles the user input and responds to the user.
     */
    @FXML
    private void handleUserInput() {
        showUserDialog(userInput.getText());
        duke.handleUserInput(userInput.getText());
        userInput.clear();
    }

    /**
     * Creates and shows the duke's dialog box.
     *
     * @param text Text to show.
     */
    private void showDukeDialog(String text) {
        assert dukeImage != null : "duke icon not found";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    /**
     * Creates the user's dialog box echoing the user's input.
     *
     * @param text Text to show.
     */
    private void showUserDialog(String text) {
        assert userImage != null : "user icon not found";
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(text, userImage)
        );
    }

}

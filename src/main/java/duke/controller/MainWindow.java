package duke.controller;

import duke.Duke;
import duke.ui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    public static final String START = "I am The Task Mechanic!\n"
            + "How can I help you today?\n" + "\nHere are the commands:\n"
            + "1. Add task - todo {description}\n"
            + "2. Add event - event {description} /from {date} /to {date}\n"
            + "3. Add deadline - deadline {description} /by {YYYY-MM-DD} HH:MM\n"
            + "4. Mark task as done - mark {task no}\n"
            + "5. Mark task as not done - unmark {task no}\n"
            + "6. Find tasks in list which matches - find {word/phrase}\n"
            + "7. Tag task with description - tag {task no} {tag description}\n"
            + "8. Show all tasks - list\n"
            + "9. Remove task - delete {task no}\n"
            + "10. End Program - bye";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialise and starts the ui for the bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Method to set the local variable d as the intended duke object.
     *
     * @param d duke object to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Method to display welcome message to user.
     */
    public void sendStart() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(START, dukeImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.run(input);
        assert !response.equals("") : "Execution output should not be empty.";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}

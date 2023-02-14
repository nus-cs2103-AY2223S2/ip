package duke.javafx;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private TaskList tasks;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    /** To initalise the main stage of JavaFX*/
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.tasks = loadTextFile();
        String intro = "Hurr..... I'm Duke.....";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(intro, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private TaskList loadTextFile() {
        Storage.createDataDir();
        TaskList tasks = new TaskList(Storage.load());
        return tasks;
    }


    /**
     * Creates two dialog boxes, one taking user's command and the other
     * returning Duke's reply to the command. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tasks.handleInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

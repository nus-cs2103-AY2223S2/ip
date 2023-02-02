package duke.ui;

import duke.Duke;
import duke.utils.ReplyString;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    // Solution adapted from
    // https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox displayViewBox;
    @FXML
    private TextField inputField;

    // Variables not used currently but may be used in the future.
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userOne.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(displayViewBox.heightProperty());
        displayViewBox.getChildren().addAll(
                DialogBox.getDukeDialog(ReplyString.onStartup, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // TODO: Handle errors properly
        String input = inputField.getText();
        String response = duke.getResponse(input);
        String dukeHeardThis = "Duke heard: " + input;
        if (Objects.equals(response, "")) {
            // TODO: Check if application exits properly
            duke.saveTasks();
            Platform.exit();
            return;
        }
        displayViewBox.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(dukeHeardThis, dukeImage),
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog(ReplyString.promptQuestion, dukeImage)
        );
        inputField.clear();
    }
}

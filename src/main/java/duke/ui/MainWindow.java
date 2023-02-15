package duke.ui;

import java.util.Objects;

import duke.Duke;
import duke.utils.ReplyString;
import javafx.application.Platform;
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
    // Solution adapted from
    // https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox displayViewBox;
    @FXML
    private TextField inputField;

    private Duke duke;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/userOne" + ".png")));
    private Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/duke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(displayViewBox.heightProperty());
        displayViewBox.getChildren().addAll(
                DialogBox.getDukeDialog(ReplyString.getOnStartupString(), dukeImage)
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
        String rawResponse = duke.getResponse(input);
        displayViewBox.getChildren().add(DialogBox.getUserDialog(input, userImage));

        if (Objects.equals(rawResponse, "")) {
            // TODO: Check if application exits properly
            displayViewBox.getChildren().addAll(
                    DialogBox.getDukeDialog(ReplyString.getGoodbyeString(), dukeImage));
            duke.saveTasks();
            Platform.exit();
            return;
        }

        String finalResponse = rawResponse + "\n\n" + ReplyString.getPromptQuestionString();
        displayViewBox.getChildren().add(DialogBox.getDukeDialog(finalResponse, dukeImage));
        inputField.clear();
    }
}

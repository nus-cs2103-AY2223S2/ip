package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/daruma.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/arisaka.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        duke = new Duke();
        duke.loadTaskList("data.txt");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.getStartUpMsg(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DukeResponse response = duke.genResponse(input);

        if (response.shouldExit) {
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.message, dukeImage)
        );

        userInput.clear();
    }

    public void saveDukeData() {
        duke.saveTaskList();
    }

    public Duke getDuke() {
        return duke;
    }
}

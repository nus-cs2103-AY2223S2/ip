package duke.ui;

import duke.Duke;
import duke.DukeException;
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

    private Duke duke = new Duke();

    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the gui for Duke.
     * Displays the welcome message on startup.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getWelcome(), dukeImage)
        );
        String loadErrorMessage = duke.getLoadErrorMessage();
        if (loadErrorMessage != null) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(loadErrorMessage, dukeImage)
            );
        }

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
        String input = userInput.getText();
        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().add(userDialogBox);

        String response = "";
        DialogBox dukeDialogBox = null;
        try {
            response = duke.getResponse(input);
            dukeDialogBox = DialogBox.getDukeDialog(response, dukeImage);
        } catch (DukeException e) {
            dukeDialogBox = DialogBox.getErrorDialog(e.getMessage(), dukeImage);
        }
        dialogContainer.getChildren().add(dukeDialogBox);

        userInput.clear();
    }
}

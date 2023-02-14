package duke.gui;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tiger.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/owl.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        String commandInput = userInput.getText();
        displayMessage(Profile.USER, commandInput);
        String response = duke.getResponse(commandInput);
        userInput.clear();

        displayMessage(Profile.DUKE, response);

    }

    @FXML
    public void displayMessage(Profile profile, String message) {
        DialogBox dialogBox;
        switch(profile) {
        case USER:
            dialogBox = DialogBox.getUserDialog(message, userImage);
            break;
        case DUKE:
            dialogBox = DialogBox.getDukeDialog(message, dukeImage);
            break;
        default:
            dialogBox = DialogBox.getDukeDialog(message, dukeImage);
        }

        dialogContainer.getChildren().add(dialogBox);
    }
}

package duke.component;

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

    private Image userImage = new Image(getClass().getResourceAsStream("/images/Reimu_1.jpg"));
    private Image dukeImage = new Image(getClass().getResourceAsStream("/images/Patchouli_2.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "FUCKK";
        DialogBox userBox = new DialogBox(input, userImage);
        DialogBox dukeBox = new DialogBox(response, dukeImage);
        dukeBox.flip();
        dialogContainer.getChildren().addAll(userBox, dukeBox);
        userInput.clear();
    }
}


package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private Pane background;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/zoro.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/chopper.png"));

    /**
     * Initializes the application.
     */
    @FXML
    public void initialize() {
        this.duke = new Duke();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.showWelcomeMessage(), dukeImage, false)
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
        String text = userInput.getText();
        Pair<String, Boolean> response = duke.getResponse(text);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(text, userImage),
                DialogBox.getDukeDialog(response.getKey(), dukeImage, response.getValue())
        );
        userInput.clear();
    }
}

package clippy.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import clippy.Clippy;
import clippy.ui.DialogBox;
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

    private Clippy clippy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserChadderson.jpeg"));
    private Image clippyImage = new Image(this.getClass().getResourceAsStream("/images/Clippy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setClippy(Clippy c) {
        clippy = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = clippy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.createUserDialog(input, userImage),
                DialogBox.createClippyDialog(response, clippyImage)
        );
        userInput.clear();
    }
}

package pix;

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

    private Pix pix;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Sanic.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/chatbot_icon.png"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getPixDialog("Hello, this is Pix!\n\nHow can I help you?", dukeImage));
    }

    public void setPix(Pix p) {
        pix = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them eventEnd the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pix.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPixDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

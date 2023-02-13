package duke.fx;

import java.util.function.Consumer;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.PNG"));
    private Image melImage = new Image(this.getClass().getResourceAsStream("/images/MEL.PNG"));

    private Consumer<String> onInputHandler;

    /**
     * Properties to set for when initializing javaFX.
     */
    @FXML
    private void initialize() {
        // Enables auto scroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input and displays the dialog box for the user.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );

        userInput.clear();

        if (onInputHandler != null) {
            onInputHandler.accept(input);
        }
    }

    /**
     * Displays MEL's reply.
     *
     * @param reply String of MEL's reply.
     */
    public void displayReply(String reply) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(reply, melImage)
        );
    }

    /**
     * Sets the input handler to handle user input.
     *
     * @param onInputHandler Input handler.
     */
    public void setOnInputHandler(Consumer<String> onInputHandler) {
        this.onInputHandler = onInputHandler;
    }
}

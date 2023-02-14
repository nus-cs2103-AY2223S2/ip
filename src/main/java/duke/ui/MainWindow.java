package duke.ui;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Consumer<String> onInputHandler;

    /**
     * Initializes the main window's nodes.
     */
    @FXML
    public void initialize() {
        dialogContainer.setBackground(new Background(
                new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Shows the dialog box for the user.
     * Calls the input handler.
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
     * Displays Duke's reply.
     *
     * @param reply Text containing Duke's reply.
     */
    public void showReply(String reply) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(reply, dukeImage)
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

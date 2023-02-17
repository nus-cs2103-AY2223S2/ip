package vic;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import vic.gui.DialogBox;

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

    private Vic vic;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.png")));
    private final Image vicImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/vicbot.png")));

    /**
     * Initializes the MainWindow stage and print the greeting to user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "\"Hello I am Vic!\nHow can I help you\"";
        dialogContainer.getChildren().addAll(
                DialogBox.getVicDialog(greeting, vicImage)
        );
    }

    public void setVic(Vic vic) {
        this.vic = vic;
    }

    /**
     * Creates two dialog boxes, one echoing user input
     * and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = vic.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVicDialog(response, vicImage)
        );
        userInput.clear();
    }
}

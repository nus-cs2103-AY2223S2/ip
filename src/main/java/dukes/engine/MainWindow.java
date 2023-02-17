package dukes.engine;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Label label;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/female.png")));
    private Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/male.png")));

    /**
     * Initialise the scroll pane property, used by FXML
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // validateUserInput(); // if you type nothing and try to send, will be ignored
        label.setText("DUKE CHAT ENGINE");
        label.setFont(new Font("Courier New", 28));
        scrollPane.setContent(dialogContainer);
        scrollPane.setStyle("-fx-font-size: 18px;");
        dialogContainer.setStyle("-fx-font-size: 13px; -fx-background-color: #FBF4C9;");

    }

    /**
     * Set duke engine
     *
     * @param d the duke engine used.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void validateUserInput() {
        sendButton.setDisable(userInput.getText().length() == 0);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }
}

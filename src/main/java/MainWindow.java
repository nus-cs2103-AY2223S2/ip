import smudge.main.Smudge;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;


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

    private Smudge smudge;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Smudge.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setStyle("-fx-font-size: 25px; -fx-background-color:black; -fx-scroll-bar-color: black;");
        greetUser();
    }

    public void setSmudge(Smudge s) {
        smudge = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Smudge's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = smudge.getResponse(input);
        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
    @FXML
    private void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Smudge the cat\n" + "What can I do for you?", dukeImage)
        );
    }
}
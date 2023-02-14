package Duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for Duke.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Carmen.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Shane.png"));

    /**
     * initialized to print greetings
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = "Hi, I'm Shane from Duke\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, dukeImage));
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
        String input = userInput.getText();
        String response = duke.ui.respondInput(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        if (response.equals("Bye. Hope to see you again soon!")) {
            System.exit(0);
        }
        userInput.clear();
    }
}

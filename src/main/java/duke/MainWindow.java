package duke;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/newGru.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/GruDuke.png"));

    private String welcomeMessage = "WOOF! Bello Boss!\nThe Minions are so useless, let Kyle assist you!\n"
            + "What can Kyle do for you today?";
    private String exitMessage = "WOOF WOOF WOOF! Kyle is sad to see you leave!";

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox firstDialogBox = DialogBox.getDukeDialog(welcomeMessage, dukeImage);
        dialogContainer.getChildren().add(firstDialogBox);
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals(exitMessage)) {
            System.exit(0);
        }
    }
}

package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextArea userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Sets the setting for GUI display.
     */
    @FXML
    public void initialize() {
        userInput.setWrapText(true);
        userInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleUserInput();
            }
        });

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates three dialog boxes, displaying the welcome message, previous tasks, and message asking user for command.
     * Clears the user input after processing.
     */
    public void setDuke(Duke d) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getWelcomeResponse(), dukeImage),
                DialogBox.getDukeDialog(duke.getPreviousTaskResponse(), dukeImage),
                DialogBox.getDukeDialog(duke.getAskForTaskResponse(), dukeImage)
        );

        userInput.clear();
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
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog(duke.getAskForTaskResponse(), dukeImage)
        );

        userInput.clear();

        if (duke.getIsEnd()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished((event) -> Platform.exit());
            delay.play();
        }
    }

}

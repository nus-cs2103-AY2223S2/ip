package duke.fxui;

import duke.Duke;
import duke.command.ExitCommand;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The main GUI window where the user input and chatbot responses are shown. The
 * user can key in their commands to be processed by the chatbot here and obtain
 * the corresponding response.
 */
public class MainWindow extends VBox {
    /**
     * The profile image of the user.
     */
    @SuppressWarnings("ConstantConditions")
    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/user.png"));
    /**
     * The profile image of the chatbot.
     */
    @SuppressWarnings("ConstantConditions")
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    /**
     * The scrollPane responsible for allowing the ability to scroll through user
     * input and responses by the chatbot.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * The container that stores all the dialog boxes by the user and chatbot.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * The text field where users key in their commands.
     */
    @FXML
    private TextField userInput;
    /**
     * The button for user to click to send their command.
     */
    @FXML
    private Button sendButton;
    /**
     * The chatbot instance.
     */
    private Duke duke;
    /**
     * Delay of 2 second when the program is exiting.
     */
    private final PauseTransition delay = new PauseTransition(Duration.seconds(2));

    /**
     * Initialises the scrollPane to have a container that contains all the dialog
     * boxes so that it is scrollable.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets a reference that the duke chatbot started and shows the welcome
     * message by the chatbot.
     *
     * @param duke Duke instance
     */
    public void setDuke(Duke duke) {
        this.duke = duke;

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcome(), dukeImage));
        delay.setOnFinished(event -> Platform.exit());
    }

    /**
     * Handles the command by the user. The command by the user is processed and a
     * response is obtained. Dialog boxes for the user command and the chatbot
     * response is displayed in the GUI. The text field is cleared afterwards.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

        assert userInput.getText().equals("");

        if (response.equals(ExitCommand.EXIT_MSG)) {
            delay.play();
        }
    }
}

package duke.client.components;

import duke.Duke;
import duke.DukeException;
import duke.client.components.DialogBox;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/doomer.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/chad.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showStartMessage();
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
        displayMessage(input, MessageOwner.USER);

        String response;
        try {
            response = duke.executeCommand(input);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        if (response.equals(Duke.TERMINATION_INDICATION)) {
            showExitMessage();
            // TODO: Exit program
            return;
        }
        displayMessage(response, MessageOwner.DUKE);
        userInput.clear();
    }

    public void showStartMessage() {
        displayMessage(GREET_MESSAGE + LOGO, MessageOwner.DUKE);
    }
    public void showExitMessage() {
        displayMessage(EXIT_MESSAGE, MessageOwner.DUKE);
    }

    private void displayMessage(String message, MessageOwner owner) {
        Image ownerImage = owner.equals(MessageOwner.DUKE) ? dukeImage : userImage;
        boolean isFlipped = owner.equals(MessageOwner.DUKE);
        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(message, ownerImage, isFlipped)
        );
    }

    public static final String EXIT_MESSAGE = "Farewell. Always at your service.";
    public static final String GREET_MESSAGE = "Hello, I'm Ekud! What can I do for you?";
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    enum MessageOwner {
        DUKE,
        USER
    }
}
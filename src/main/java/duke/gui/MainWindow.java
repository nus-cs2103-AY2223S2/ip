package duke.gui;

import duke.Duke;
import duke.commands.ByeCmd;
import duke.commands.Command;
import duke.exceptions.CommandExecutionError;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String commandInput = userInput.getText();
        userInput.clear();
        Command command = duke.getCommand(commandInput);
        String response;
        try {
            response = command.execute();
        } catch (CommandExecutionError e) {
            response = "I couldn't do what you asked for. \n" + e.toString();
        }

        displayMessage(Profile.USER, commandInput, duke.isSuccessfulExecution(response));
        displayMessage(Profile.DUKE, response, duke.isSuccessfulExecution(response));

        if (command instanceof ByeCmd) {
            Platform.exit();
        }
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public void displayMessage(Profile profile, String message, boolean highlightError) {
        DialogBox dialogBox;
        switch(profile) {
        case USER:
            dialogBox = DialogBox.getUserDialog(message, highlightError);
            break;
        case DUKE:
            dialogBox = DialogBox.getDukeDialog(message, highlightError);
            break;
        default:
            dialogBox = DialogBox.getDukeDialog(message, highlightError);
        }

        dialogContainer.getChildren().add(dialogBox);
    }

    public void greetUser() {
        displayMessage(Profile.DUKE, "Hoot! How may I assist you on this fine night?", false);
    }
}

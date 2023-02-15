package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    public MainWindow(Duke d) {
        duke = d;
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        String response = duke.getResponse(commandInput);
        displayMessage(Profile.USER, commandInput, duke.isSuccessfulExecution(response));
        userInput.clear();
        displayMessage(Profile.DUKE, response, duke.isSuccessfulExecution(response));
    }

    @FXML
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

    @FXML
    public void greetUser() {
        displayMessage(Profile.DUKE, "Hoot! How may I assist you on this fine night?", false);
    }
}

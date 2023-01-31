package duke.gui;

import java.util.concurrent.TimeUnit;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.PNG"));

    /**
     * Initialise the scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Duke object to current duke and print the display message to the user with the dukeImage.
     *
     * @param d Duke object to be stored
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getBotDialog(d.getGreeting(), botImage));
    }

    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        showDialogueBoxes(input, response);
        userInput.clear();
        checkExit(response);
    }

    /**
     * Handle dialogue boxes visuals.
     *
     * @param usrInput user input.
     * @param response response from Ai-chan.
     */
    private void showDialogueBoxes(String usrInput, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(usrInput, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
    }

    /**
     * Check if response needs an exit.
     *
     * @param response response from Ai-chan.
     */
    private void checkExit(String response) throws Exception {
        if (response.equals("\t Bye. See you next time! :)\n")) {
            dialogContainer.getChildren().add(
                    DialogBox.getBotDialog(response, botImage)
            );
            TimeUnit.MILLISECONDS.sleep(800);
            Platform.exit();
        }
    }
}

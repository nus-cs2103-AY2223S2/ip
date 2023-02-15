package Duke;

import java.util.Timer;
import java.util.TimerTask;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke Duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser1.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Initialise the starting screen of the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setFont(Font.font("Helvetica"));
        userInput.setFont(Font.font("Helvetica"));
        String greeting = "\tHi! I'm a Hero.\n\tHow can I help you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, dukeImage)
        );

    }


    /**
     * Set the duke for the main window.
     *
     * @param d the duke object to be set for the main window
     */
    public void setDuke(Duke d) {
        assert d != null;
        Duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
        // @@author: nate_weldon-reused
        // This code is adapted from:
        // https://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
        // with minor modification
        if (input.equals("bye")) {
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer("Delay");
            timer.schedule(task, 1000L);
        }
    }
}
package cluck.ui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

import cluck.Cluck;
import cluck.messages.Messages;
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

    private Cluck cluck;

    private final Image cluckImage = new Image(this.getClass().getResourceAsStream("/images/Cluck.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Monke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCluck(Cluck c) {
        cluck = c;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.MESSAGE_WELCOME, cluckImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cluck.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, cluckImage)
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
            timer.schedule(task, 500L);
        }
    }
}

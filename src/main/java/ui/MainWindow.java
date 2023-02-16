package ui;

import commands.EndCommand;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import parser.Parser;
import taskgenie.TaskGenie;
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

    private TaskGenie taskGenie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image taskGenieImage = new Image(this.getClass().getResourceAsStream("/images/DaTaskGenie.png"));


    /**
     * Initialise the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getTaskGenieDialog(Ui.getIntroduction(), taskGenieImage)
        );
    }

    public void setTaskGenie(TaskGenie d) {
        this.taskGenie = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TaskGenie's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskGenie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskGenieDialog(response, taskGenieImage)
        );
        userInput.clear();
        checkIfClosePlatform(input);
    }

    /**
     * Checks if the user input provided is correct before closing the platform.
     */
    private void checkIfClosePlatform(String userInput) {
        if (Parser.parseCommand(userInput) instanceof EndCommand) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}

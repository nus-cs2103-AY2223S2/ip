package duke;

import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Represents the controller for the MainWindow. Provides the layout for the other controls. */
public class MainWindow extends AnchorPane {
    //Controls in the main window
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    //Duke that runs in the main window instance
    private Duke duke;

    //Stage to be closed if application encounters a bye command
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ghost.png"));

    /**
     * Initialises the main window controller and generates starting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        generateStartingMessage();
    }

    /**
     * Sets the Duke instance associated with the application.
     *
     * @param duke The Duke instance that runs in the application.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Sets the stage associated with this main window.
     *
     * @param stage The stage that is responsible for displaying the application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing the user input and the other containing the bot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        //Exit the application
        if (input.equals("bye")) {
            PauseTransition delayBeforeClosing = new PauseTransition(Duration.seconds(5));
            //Closes the stage after the specified duration
            delayBeforeClosing.setOnFinished(event -> stage.close());
            delayBeforeClosing.play();
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Generates the starting message from the bot.
     */
    private void generateStartingMessage() {
        String dukeText = Ui.LOGO + "\n\n" + Ui.INTRODUCTORY_BODY + "\n\n" + Ui.COMMAND_LIST;

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
    }
}

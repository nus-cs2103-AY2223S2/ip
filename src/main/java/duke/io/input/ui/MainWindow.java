package duke.io.input.ui;

import java.io.IOException;

import duke.util.Storage;
import duke.workflow.Event;
import duke.workflow.Greeting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private ChatBot chatbot = new ChatBot();

    private int firstTimeRunningDukeFlag = -1;

    private Event currentEvent;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Doraemon.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Vader.jpg"));

    /**
     * Construct a MainWindow class containingan AnchorPane, VBox, ScrollPane, TextField, and Button
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();;
        }
    }

    /**
     * Run when the GUI version of Duke is initialized
     */

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }



    /**
     * Greet the user when he opens Duke.
     */

    public void greet() {
        Greeting greeting = new Greeting();
        String response = UserInterface.printGuiLogo();
        String toPrintOut = greeting.toString();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog(toPrintOut, dukeImage));
    }

    /**
     * Create two dialogs boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Output response based on whether the user wants to run Duke
     */

    private int runDuke() {
        String isPlaying = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(isPlaying, userImage));
        if (isPlaying.equals("NO")) {
            Greeting greeting = new Greeting();
            Event nextEvent = greeting.toNextEvent("NOT PLAYING");
            this.currentEvent = nextEvent;
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(nextEvent.toString(), dukeImage));
            return 0;
        } else if (isPlaying.equals("YES")) {
            Greeting greeting = new Greeting();
            Event nextEvent = greeting.toNextEvent("PLAYING");
            this.currentEvent = nextEvent;
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(nextEvent.toString(), dukeImage));

            return 1;
        } else {
            String warning = "FOCUS, HUMAN. "
                    + "YOU ARE TO ENTER INPUT WITH FULL CAPS.";
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(warning, dukeImage));
            return -1;
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        getScene().getWindow().sizeToScene();
        if (this.firstTimeRunningDukeFlag < 0) {
            firstTimeRunningDukeFlag = runDuke();
        } else if (firstTimeRunningDukeFlag > 0) {
            if (!this.currentEvent.isFinalEvent()) {
                String input = userInput.getText();
                userInput.clear();
                this.currentEvent = this.currentEvent.toNextEvent(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(this.currentEvent.toString(), dukeImage));
                if (this.currentEvent.isFinalEvent()) {
                    String saveProgressQuery = "SAVE YOUR GRAND PLAN FOR ANOTHER DAY? ";
                    dialogContainer.getChildren().add(
                            DialogBox.getDukeDialog(saveProgressQuery, dukeImage));
                }
            } else {
                String input = userInput.getText();
                userInput.clear();

                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage));

                Storage.saveProgressGUI(input, this.currentEvent.getTaskList());
                System.exit(0);
            }
        } else {
            return;
        }
    }
}

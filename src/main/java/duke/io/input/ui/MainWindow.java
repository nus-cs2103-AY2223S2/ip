package duke.io.input.ui;

import java.io.IOException;

import duke.io.input.ui.DialogBox;

import duke.workflow.Event;
import duke.workflow.Greeting;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

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

    private UserInteraction chatbot = new UserInteraction();

    private int runningDuke = -1;

    private Event currentEvent;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Doraemon.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Vader.jpg"));

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    public void greet() {
        Greeting greeting = new Greeting();
        String response = chatbot.printGuiLogo();
        String toPrintOut = greeting.toString();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog(toPrintOut, dukeImage));
    }

    private int runDuke() {
        String isPlaying = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(isPlaying, userImage));
        if (isPlaying.equals("NO")) {
            Greeting greeting = new Greeting(0);
            Event nextEvent = greeting.toNext();
            this.currentEvent = nextEvent;
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(nextEvent.toString(), dukeImage));
            return 0;
        } else if (isPlaying.equals("YES")) {
            Greeting greeting = new Greeting(1);
            Event nextEvent = greeting.toNext();
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

    @FXML
    private void handleUserInput() {
        if (this.runningDuke < 0) {
            runningDuke = runDuke();
        } else if (runningDuke > 0) {
            if (this.currentEvent.getStatus() == false) {
                String input = userInput.getText();
                userInput.clear();
                this.currentEvent = this.currentEvent.toNextGui(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(this.currentEvent.toString(), dukeImage));
            } else {
                String input = userInput.getText();
                userInput.clear();
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog("Sup Bro", dukeImage));
            }
        } else {
            return;
        }
    }
}

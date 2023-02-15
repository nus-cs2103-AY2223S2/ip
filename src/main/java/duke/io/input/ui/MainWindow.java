package duke.io.input.ui;

import java.io.IOException;

import duke.io.input.ui.DialogBox;

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

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();






        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog("Sup Bro", dukeImage)
        );
        userInput.clear();
    }
}

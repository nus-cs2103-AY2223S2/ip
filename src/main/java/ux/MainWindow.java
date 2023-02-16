//@@author 9fc70c892-reused
//Source: https://se-education.org/guides/tutorials/javaFx.html
//Attempted to write my own but took too much time.
//@@author
package ux;

import javafx.fxml.FXML;
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

    private core.Duke duke;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * This method initializes the GUI and greets the user.
     *
     * @param d The duke object used in the running of the application.
     */
    public void setDuke(core.Duke d) {
        duke = d;
        String response = core.Duke.greet();
        String initResponse = duke.initialize();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog(initResponse, dukeImage)
        );
    }

    /**
     * This method creates one dialog box containing Duke's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

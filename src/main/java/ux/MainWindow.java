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

//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(core.Duke d) {
        duke = d;

//        System.out.println(core.Duke.greet());
        String response = core.Duke.greet();
        String initResponse = duke.initialize();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog(initResponse, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing core.Duke's reply and then appends them
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

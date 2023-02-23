package Twofort.graphics;

import Twofort.Twofort;
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

    private Twofort twofort;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Scout.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Engineer.png"));
    private Image dukeErrorImage = new Image(this.getClass().getResourceAsStream("/images/EngineerError.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTwofort(Twofort t) {
        twofort = t;
        getWelcomeMessage();
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = twofort.getResponse(input);
        if(response.split(" ",2)[0].equals("[E]")){ //error message
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeErrorImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }

    private void getWelcomeMessage(){
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Howdy pardner! It's time to get to work!", dukeImage));
    }
}
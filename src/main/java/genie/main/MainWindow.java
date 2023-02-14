package genie.main;

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

    private Genie genie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/genie.jpeg"));

    @FXML
    public void initialize() {
        this.genie = new Genie();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = showGreeting(genie);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, dukeImage));
    }

    public void setDuke(Genie g) {
        genie = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = genie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
    @FXML
    private String showGreeting(Genie genie) {
        String greeting = genie.showGreetingMessage();
        return greeting;
    }
}
package duke;

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

    private GUI gui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpeg"));

    @FXML
    public void initialize() throws Exception {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setGui(GUI g) throws Exception {
        gui = g;
        String startMsg = gui.getResponse("initialise");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMsg, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception{
        String input = userInput.getText();
        String response = gui.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("\n" + input + "      " , userImage),
                DialogBox.getDukeDialog("\n" + response, dukeImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}



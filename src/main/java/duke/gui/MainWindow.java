package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Ui;
import duke.enums.Views;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        boolean isError = false;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            isError = true;
            response = Ui.stringError(e);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, isError));
        userInput.clear();

        // Exit gracefully
        // Inspiration from https://github.com/nus-cs2103-AY2223S2/forum/issues/99
        if (response.equals(Views.END_STRING.str())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));

            pause.setOnFinished(event -> {
                Platform.exit();
                // It was not exiting. https://stackoverflow.com/a/20489749
                System.exit(0);
            });

            // start the pause
            pause.play();
        }
    }
}

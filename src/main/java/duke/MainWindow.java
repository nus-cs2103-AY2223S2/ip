package duke;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void sendWelcomeMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput(Event event) {
        String input = userInput.getText();

        if (input.equals("bye")) {
            handleExitProgram(event);
        }

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void handleExitProgram(Event event) {
        Object o = event.getSource();

        if (o instanceof Button) {
            Button button = (Button) event.getSource();
            Window window = button.getScene().getWindow();
            Stage stage = (Stage) window;
            stage.close();
        } else {
            TextField textField = (TextField) event.getSource();
            Window window = textField.getScene().getWindow();
            Stage stage = (Stage) window;
            stage.close();
        }
    }
}
package gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static java.lang.Thread.sleep;

/**
 * Controller for gui.MainWindow. Provides the layout for the other controls.
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.JPG"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.JPG"));

    /**
     * Initializes the gui.MainWindow layout and loads the data stored.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = "Hi~ I'm duke.Duke>_<\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, dukeImage));

        duke = new Duke();
        String output = duke.loadDataFromDisk();
        if (output == "No data storage file exists.") {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(output, dukeImage));
        }
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (response == "Bye~ Hope to see you again soon:)") {
            sleep(3000);
            Platform.exit();
        }
        userInput.clear();
    }
}

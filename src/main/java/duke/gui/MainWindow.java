package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Ui ui;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/r2d2.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/c3po.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke, Ui ui) {
        this.duke = duke;
        this.ui = ui;
    }

    public void setWelcomeMessage() {
        ui.printWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.getMessage().toString(), dukeImage));
        ui.clearMessage();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        //@@author amoonguss1-reused
        //Reused from https://github.com/amoonguss1/ip/commit/d94530b862bc089f5c2d1025fb5737b06283cae6
        // with minor modifications
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
        //@@author
    }
}
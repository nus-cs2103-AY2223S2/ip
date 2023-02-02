package duke.controller;

import duke.Duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
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
    private Button enterButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/FakeDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Processes user input and prints out Duke response.
     */
    @FXML
    private void processUserInput() {
        String input = userInput.getText();
        try {
            String response = getResponse(input);
            dialogContainer.getChildren().addAll(
                    new DialogBox(input, userImage).getUserDialog(input, userImage),
                    new DialogBox(input, dukeImage).getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    new DialogBox(input, userImage).getUserDialog(input, userImage),
                    new DialogBox(input, dukeImage).getDukeDialog(e.getMessage(), dukeImage)
            );
        }
        userInput.clear();
    }

    /**
     * Generates Fake Duke's response for user.
     *
     * @param input User input to Fake Duke.
     * @throws DukeException Exception thrown from Duke package methods.
     */
    private String getResponse(String input) throws DukeException {
        String fullCommand = duke.getUi().readCommand(input);
        Command c = new Parser().parse(fullCommand);
        return c.execute(duke.getTasks(), duke.getUi(), duke.getStorage());
    }
}
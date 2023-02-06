package duke.controller;

import duke.Duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        dialogContainer.getChildren().addAll(
                new DialogBox(new Ui().getWelcome(), dukeImage).getDukeDialog()
        );
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
                    new DialogBox(input, userImage).getUserDialog(),
                    new DialogBox(response, dukeImage).getDukeDialog()
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    new DialogBox(input, userImage).getUserDialog(),
                    new DialogBox(e.getMessage(), dukeImage).getDukeDialog()
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
        if (c instanceof ExitCommand) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
        return c.execute(duke.getTasks(), duke.getUi(), duke.getStorage());
    }
}
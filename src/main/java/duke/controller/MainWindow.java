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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/FakeDuke.png"));

    /**
     * Initializes the main window and displays welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        createDukeDialogBox(Ui.getWelcome(), dukeImage);
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
            createUserDialogBox(input, userImage);
            createDukeDialogBox(response, dukeImage);
        } catch (DukeException e) {
            createUserDialogBox(input, userImage);
            createDukeDialogBox(e.getMessage(), dukeImage);
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
        String fullCommand = Ui.readCommand(input);
        Command c = Parser.parse(fullCommand);
        boolean isExitCommand = c instanceof ExitCommand;
        if (isExitCommand) {
            System.out.println(c.execute(duke.getTasks(), duke.getStorage()));
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
            return "";
        } else {
            return c.execute(duke.getTasks(), duke.getStorage());
        }
    }

    /**
     * Creates a user dialog box using the label and image passed into this method.
     *
     * @param label Text to be displayed on the dialog box.
     * @param image Image to be displayed on the dialog box.
     */
    private void createUserDialogBox(String label, Image image) {
        dialogContainer.getChildren().addAll(
                new DialogBox(label, image).getUserDialog()
        );
    }

    /**
     * Creates a duke dialog box using the label and image passed into this method.
     *
     * @param label Text to be displayed on the dialog box.
     * @param image Image to be displayed on the dialog box.
     */
    private void createDukeDialogBox(String label, Image image) {
        dialogContainer.getChildren().addAll(
                new DialogBox(label, image).getDukeDialog()
        );
    }
}

package duke.fxui;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import duke.Duke;
import duke.DukeException;
import duke.command.ExitCommand;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The main GUI window where the user input and chatbot responses are shown. The
 * user can key in their commands to be processed by the chatbot here and obtain
 * the corresponding response.
 */
public class MainWindow extends VBox {
    /**
     * Minimum width of the alert box when shown.
     */
    private static final int MIN_WIDTH = 700;
    /**
     * The profile image of the user.
     */
    @SuppressWarnings("ConstantConditions")
    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/user.png"));
    /**
     * The profile image of the chatbot.
     */
    @SuppressWarnings("ConstantConditions")
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    /**
     * The scrollPane responsible for allowing the ability to scroll through user
     * input and responses by the chatbot.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * The container that stores all the dialog boxes by the user and chatbot.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * The text field where users key in their commands.
     */
    @FXML
    private TextField userInput;
    /**
     * The chatbot instance.
     */
    private Duke duke;
    /**
     * Delay of 2 second when the program is exiting.
     */
    private final PauseTransition delay = new PauseTransition(Duration.seconds(2));
    /**
     * Alert box to show the commands or about information when clicking on menu items.
     */
    private final Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.CLOSE);
    /**
     * Initialises the scrollPane to have a container that contains all the dialog
     * boxes so that it is scrollable.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets a reference that the duke chatbot started and shows the welcome
     * message by the chatbot.
     *
     * @param duke Duke instance
     */
    public void setDuke(Duke duke) {
        this.duke = duke;

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcome(), dukeImage));
        delay.setOnFinished(event -> Platform.exit());
        alert.getDialogPane().setMinWidth(MIN_WIDTH);
    }

    /**
     * Handles the command by the user. The command by the user is processed and a
     * response is obtained. Dialog boxes for the user command and the chatbot
     * response is displayed in the GUI. The text field is cleared afterwards.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

        assert userInput.getText().equals("");

        if (response.equals(ExitCommand.EXIT_MSG)) {
            delay.play();
        }
    }

    @FXML
    private void handleShowCommands() {
        String helpPath = "/values/help.txt";
        String helpError = "Error occurred when reading help text file.";
        String helpText = readResource(helpPath, helpError);

        alert.setContentText(helpText);
        alert.setTitle("Commands");

        alert.show();
    }

    @FXML
    private void handleShowAbout() {
        String aboutPath = "/values/about.txt";
        String aboutError = "Error occurred when reading about text file.";
        String aboutText = readResource(aboutPath, aboutError);

        alert.setContentText(aboutText);
        alert.setTitle("About");

        alert.show();
    }

    private String readResource(String path, String errorMsg) throws DukeException {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        String text;

        assert inputStream != null;

        try {
            text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(errorMsg);
        }

        return text;
    }
}

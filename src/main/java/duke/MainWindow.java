package duke;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeIcon.png"));

    /**
     * Binds the v value property to the height of the dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialises Duke and greets user.
     *
     * @param d A Duke instance.
     */
    public void setDuke(Duke d) {
        this.duke = d;

        String logo = " ____      _        \n"
                + "|  _ \\ _  _| |   ___ \n"
                + "| | | | | | | |/ /  _  \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|___/ \\__|_|\\_\\___|\n";
        String welcome = "Hello from\n" + logo;

        welcome += "Hi! I am Duke. How may I help you today?\n";

        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcome, this.dukeImage)
        );
    }

    /**
     * Gets a response from Duke for the user input given and then
     * gets one DialogBox containing the user input and another containing the response,
     * adds the two DialogBox to the Vbox container.
     * Clear the user input at the end.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

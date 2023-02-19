package duke.ui;

import duke.DialogBox;
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow.
 */
public class MainWindow extends AnchorPane {
    protected static final String WELCOME_MESSAGE = "Hello! I'm Duke!\nWhat can I do for you?";
    protected static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    protected static final String EXIT_COMMAND = "bye";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Stage stage;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cartoon_user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(("/images/cartoon_robot.png")));

    /**
     * Intialises dialogContainer properties.
     */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage));
    }

    public void setDuke(Duke d, Stage s) {
        duke = d;
        stage = s;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        userInput.clear();
        if (input.equals(EXIT_COMMAND)) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(EXIT_MESSAGE, dukeImage));
            stage.close();
            return;
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}

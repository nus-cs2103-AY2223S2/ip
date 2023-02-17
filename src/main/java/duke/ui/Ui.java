package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Represents the user interface of Duke. Currently, just terminal text.
 */
public class Ui extends AnchorPane {

    private String response;
    private String commandInput;

    private Duke duke;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;


    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Duke.jpg")));
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/User.jpg")));

    public Ui() {
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        duke.setUi(this);
    }

    /**
     * Initializes the UI.
     */
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.dialogContainer.getStyleClass().add("dialogBox");
    }

    @FXML
    private void handleUserInput() {
        String text = this.userInput.getText();
        MessageBox userResponse = MessageBox.getUserDialog(text, this.userImage);
        this.dialogContainer.getChildren().add(userResponse);
        this.duke.runCommand(text);
        this.userInput.clear();
    }

    /**
     * Stores the response given by Duke.
     *
     * @param reply the reply given by Duke.
     */
    public void response(String reply) {
        this.response = reply;
    }

    /**
     * Prints out the response given by Duke.
     */
    public void showResponse() {
        MessageBox dukeResponse = MessageBox.getDukeDialog(this.response, this.dukeImage);
        this.dialogContainer.getChildren().add(dukeResponse);
    }
}

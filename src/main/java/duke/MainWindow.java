package duke;

import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/space_Lin.jpg"));

    private String dukeStyle = "-fx-font-size: 12pt;\n" +
            "    -fx-font-family: \"Helvetica\";\n" +
            "    -fx-text-fill: black;\n" +
            "    -fx-background-color: #e9967a;\n" +
            "    -fx-font-style: normal;";

    private String userStyle = "-fx-font-size: 14pt;\n" +
            "    -fx-font-family: \"Courier\";\n" +
            "    -fx-text-fill: black;\n" +
            "    -fx-background-color: #00ced1;\n" +
            "    -fx-font-style: normal;\n" +
            "    -fx-font-weight: normal;";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Greetings, human. I am Space Lin, goddess of servant universe. Tell me what you need.";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, dukeImage, dukeStyle));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response == null) {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, userStyle),
                DialogBox.getDukeDialog(response, dukeImage, dukeStyle)
        );
        userInput.clear();
    }
}

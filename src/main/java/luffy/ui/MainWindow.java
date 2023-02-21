package luffy.ui;

import luffy.Luffy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author wendy0107-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with some modifications

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final String HELLO_MSG = "Hello! I'm Luffy.\nWhat can I do for you today? :)";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Luffy luffy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Luffy.png"));
    private Image luffyImage = new Image(this.getClass().getResourceAsStream("/images/LuffyReply.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLuffy(Luffy l) {
        this.luffy = l;
        dialogContainer.getChildren().addAll(DialogBox.getLuffyDialog(HELLO_MSG, luffyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Luffy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luffy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLuffyDialog(response, luffyImage)
        );
        userInput.clear();
    }
}

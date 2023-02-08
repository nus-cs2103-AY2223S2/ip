import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import leo.task.LeoTaskException;

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

    private Leo leo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image leoImage = new Image(this.getClass().getResourceAsStream("/images/leo.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLeo(Leo l) {
        leo = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws LeoTaskException {
        String input = userInput.getText();
        String response = leo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLeoDialog(response, leoImage)
        );
        userInput.clear();
        if (response.equals("It was nice talking, see you soon!\n")) {
            System.exit(0);
        }
    }
}
package iris;

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

    private Iris iris;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private final Image irisImage = new Image(this.getClass().getResourceAsStream("/images/IrisAvatar.png"));

    /**
     * initializes the window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setIris(Iris i) {
        this.iris = i;
        dialogContainer.getChildren().add(DialogBox.getIrisDialog(this.iris.startingMessage(), irisImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing iris's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = iris.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getIrisDialog(response, irisImage)
        );
        userInput.clear();
    }
}

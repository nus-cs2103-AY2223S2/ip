package peppa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private GridPane userInputContainer;

    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Peppa peppa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image peppaImage = new Image(this.getClass().getResourceAsStream("/images/Peppa.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPeppa(Peppa p) {
        this.peppa = p;
        String response = "Initialising data...\n\n";
        if (!this.peppa.isFileSet()) {
            response += peppa.getResponse("files");
            setPeppaDialog(response);
        } else {
            setPeppaDialog(response + "Oink! I'm Peppa. Nice to meet you! How can I assist you today?");
        }
    }

    public void setPeppaDialog(String msg) {
        this.dialogContainer.getChildren().add(DialogBox.getPeppaDialog(msg, peppaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = peppa.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPeppaDialog(response, peppaImage)
        );
        userInput.clear();
    }
}

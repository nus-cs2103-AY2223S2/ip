package wessy.javafxnodes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wessy.Wessy;

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

    private Wessy wessy = new Wessy();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dummy_user.jpeg"));
    private Image wessyImage = new Image(this.getClass().getResourceAsStream("/images/water walley.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String opening = wessy.startsUp();
        dialogContainer.getChildren().addAll(
                DialogBox.getWessyDialogBox(opening, wessyImage)
        );
    }

    public void setWessy(Wessy w) {
        wessy = w;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wessy.respond(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(input, userImage),
                DialogBox.getWessyDialogBox(response, wessyImage)
        );
        userInput.clear();
    }
}

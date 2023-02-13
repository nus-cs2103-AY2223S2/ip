package connor.gui;

import connor.Connor;
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

    private Connor connor;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Hank.png"));
    private Image connorImage = new Image(this.getClass().getResourceAsStream("/images/Connor.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setConnor(Connor d) {
        connor = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(d.greet(), connorImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Connor's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = connor.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, connorImage)
        );
        userInput.clear();
    }
}

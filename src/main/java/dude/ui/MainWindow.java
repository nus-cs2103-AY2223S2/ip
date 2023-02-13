package dude.ui;

import java.util.Objects;

import dude.Dude;
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

    private Dude dude;
    private Ui ui = new Ui();


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dudeImage = new Image(this.getClass().getResourceAsStream("/images/DaDude.png"));

    /**
     * Initializes the window and starts with a greeting.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDudeDialog(ui.showWelcome(), dudeImage)
        );
    }

    public void setDude(Dude d) {
        dude = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dude.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDudeDialog(response, dudeImage)
        );
        userInput.clear();
        if (Objects.equals(response, "Ciaos! See you next time\n")) {
            Platform.exit();
        }
    }
}

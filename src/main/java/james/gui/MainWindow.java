package james.gui;

import james.jamesbot.JamesBot;
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

    private JamesBot jamesBot;

    private Image jamesImage = new Image(this.getClass().getResourceAsStream("/images/ben.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dr.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getJamesDialog("Hello, I am James.\nHow may I be of service to you?", jamesImage));
        assert this.scrollPane != null : "[scrollPane] FXML was improperly configured.";
        assert this.dialogContainer != null : "[dialogContainer] FXML was improperly configured.";
        assert this.userInput != null : "[userInput] FXML was improperly configured.";
        assert this.sendButton != null : "[sendButton] FXML was improperly configured.";
    }

    public void setJames(JamesBot james) {
        jamesBot = james;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jamesBot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJamesDialog(response, jamesImage)
        );

        userInput.clear();

        if (jamesBot.isEnd(input)) {
            sendButton.setDisable(true);
            userInput.setDisable(true);
        }
    }
}

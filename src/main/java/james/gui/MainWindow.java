package james.gui;

import java.util.Objects;

import james.jamesbot.JamesBot;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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

    private final Image jamesImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/ben.png")));
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/dr.png")));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.setOnScroll(event -> scrollPane.setVvalue(
                scrollPane.getVvalue() - event.getDeltaX() / dialogContainer.getHeight()));

        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));

        sendButton.disableProperty().bind(Bindings.isEmpty(userInput.textProperty()));

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
     * Creates two dialog boxes, one echoing user input and the other containing James's reply and then appends them to
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
            Platform.exit();
        }
    }
}

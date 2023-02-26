package chattime;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// @@author Jeffry Lum-reused
// Reused from Guides for SE Student Project- Java FX Tutorial part 4
// with minor modification
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private AnchorPane anchorPane;

    private Chattime chattime;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/image/userProfile.png")));
    private Image botImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/image/botProfile.png")));

    /**
     * Initializes the GUI vision by binding the scrollPane properties to VBox.
     */
    @FXML
    public void initialize() {
        dialogContainer.getStyleClass().add("vbox-dialog");
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes bot first dialog.
     */
    public void setBot(Chattime c) {
        chattime = c;
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(chattime.greet(), botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chattime.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );

        userInput.clear();

        if (!chattime.checkRunningStatus()) {
            Platform.exit();
        }

    }
}

// @@author Jeffry Lum-reused

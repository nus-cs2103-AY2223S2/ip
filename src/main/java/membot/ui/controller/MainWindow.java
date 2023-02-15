package membot.ui.controller;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import membot.Membot;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String BOT_ICON = "/assets/botIcon.png";
    private static final String USER_ICON = "/assets/userIcon.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Membot membot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream(USER_ICON));
    private final Image botImage = new Image(this.getClass().getResourceAsStream(BOT_ICON));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMembot(Membot d) {
        this.membot = d;
    }

    public Consumer<String> getPrinter() {
        return s -> dialogContainer.getChildren().add(DialogBox.getDukeDialog(s, botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Membot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        this.membot.execute(input);
        userInput.clear();
    }
}

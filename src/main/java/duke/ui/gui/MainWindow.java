package duke.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Controller for MainWindow. Provides the Layout for the other controls.
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

    private final Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(getClass().getResourceAsStream("/images/duke.png"));

    private final BiConsumer<String, Consumer<String>> inputHandler;

    /**
     * Creates a MainWindow object.
     *
     * @param inputHandler Handles the user's input.
     */
    public MainWindow(BiConsumer<String, Consumer<String>> inputHandler) {
        assert inputHandler != null;

        this.inputHandler = inputHandler;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Prints a message that will appear to be said by Duke.
     *
     * @param message The message to be printed.
     */
    public void printDukeMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();

        printUserMessage(input);

        inputHandler.accept(input, this::printDukeMessage);
    }

    private void printUserMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }
}

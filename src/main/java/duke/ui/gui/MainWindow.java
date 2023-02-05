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
 * Controller for MainWindow.
 * <p>
 * Provides the Layout for the other controls.
 * </p>
 */
public class MainWindow extends AnchorPane {
    private static final String FXML_PATH = "/view/MainWindow.fxml";

    private static final String USER_IMG_PATH = "/images/user.png";
    private static final String DUKE_IMG_PATH = "/images/duke.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImg = new Image(getClass().getResourceAsStream(USER_IMG_PATH));
    private final Image dukeImg = new Image(getClass().getResourceAsStream(DUKE_IMG_PATH));

    private final BiConsumer<String, Consumer<String>> inputHandler;

    /**
     * Creates a MainWindow object.
     *
     * @param inputHandler Handles the user's input.
     */
    public MainWindow(BiConsumer<String, Consumer<String>> inputHandler) {
        assert inputHandler != null;

        this.inputHandler = inputHandler;

        loadFxml();
    }

    /**
     * Prints a message that will appear to be said by Duke.
     *
     * @param message The message to be printed.
     */
    public void printDukeMessage(String message) {
        DialogBox dialogBox = DialogBox.getDukeDialog(message, dukeImg);
        dialogContainer.getChildren().add(dialogBox);
    }

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();

        printUserMessage(input);

        inputHandler.accept(input, this::printDukeMessage);
    }

    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));

            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);

            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printUserMessage(String message) {
        DialogBox dialogBox = DialogBox.getUserDialog(message, userImg);
        dialogContainer.getChildren().add(dialogBox);
    }
}

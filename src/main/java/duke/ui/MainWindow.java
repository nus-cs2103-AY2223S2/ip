package duke.ui;
import java.io.IOException;
import java.util.Stack;
import java.util.function.Consumer;

import duke.Duke;
import duke.constant.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Stack<String> keyLog;
    private String originalText = "";

    /**
     * Constructs GUI main window.
     *
     * @param duke {@link Duke} duke agent
     */
    public MainWindow(Duke duke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            // Say welcome
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(Message.WELCOME, dukeImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setPannable(true);

        this.duke = duke;
        keyLog = new Stack<>();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        keyLog.push(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        duke.getResponse(input, (ty, reply) -> {
            switch (ty) {
            case ERROR:
                dialogContainer.getChildren().add(DialogBox.getDukeErrorDialog(reply, dukeImage));
                break;
            case WARNING:
                dialogContainer.getChildren().add(DialogBox.getDukeWarningDialog(reply, dukeImage));
                break;
            default:
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(reply, dukeImage));
                break;
            }
        });
        originalText = "";
        userInput.clear();
    }

    /**
     * Handles key press events.
     *
     * @param e key event
     */
    @FXML
    private void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
        case UP:
            getLog(log -> {
                originalText = userInput.getText();
                userInput.setText(log);
            });
            break;
        case DOWN:
            if (!originalText.isEmpty()) {
                userInput.setText(originalText);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Retrives the last entered command into the textbox.
     *
     * @param log handler for result
     */
    private void getLog(Consumer<String> log) {
        if (!keyLog.empty()) {
            log.accept(keyLog.pop());
        }
    }

}

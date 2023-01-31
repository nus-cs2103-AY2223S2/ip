package presentation.gui;

import domain.entities.core.Writable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.controllers.DukeEventLoop;

/**
 * The main window for duke.
 */
public class MainWindow extends AnchorPane implements Writable {
    final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    DukeEventLoop dukeEventLoop;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    public void setDukeEventLoop(DukeEventLoop dukeEventLoop) {
        this.dukeEventLoop = dukeEventLoop;
    }

    @FXML
    public void initialize() {
    }

    private void write(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message,
                dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input,
                userImage));
        dukeEventLoop.runOnce();
        userInput.clear();
    }

    @Override
    public void writeln(Object content) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog
                (content.toString(), userImage));
    }

    @Override
    public void write(Object content) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        dialogContainer.getChildren().clear();
    }
}

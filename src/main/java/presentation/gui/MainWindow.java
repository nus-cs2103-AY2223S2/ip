package presentation.gui;

import domain.entities.core.ExitStatus;
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

    private DukeEventLoop dukeEventLoop;
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

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input,
                userImage));
        final ExitStatus result = dukeEventLoop.runWithCommand(input);
        if (result == ExitStatus.terminate) {
            dukeEventLoop.dispose();
            System.exit(0);
        }
        userInput.clear();
    }

    @Override
    public void writeln(Object content) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(content.toString(), dukeImage));
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

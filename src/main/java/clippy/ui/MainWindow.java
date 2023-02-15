package clippy.ui;

import clippy.Clippy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Clippy clippy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserChadderson.jpeg"));
    private Image clippyImage = new Image(this.getClass().getResourceAsStream("/images/Clippy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setClippy(Clippy c) {
        clippy = c;
    }

    /**
     * Creates one dialog box, echoing user input, before passing the input
     * to Clippy to handle. Clears user input after done.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        showUserTextbox(input);
        clippy.handleCommand(input);
        userInput.clear();
    }

    public void showUserTextbox(String text) {
        dialogContainer.getChildren().add(DialogBox.createUserDialog(text, userImage));
    }
    public void showClippyTextbox(String text) {
        dialogContainer.getChildren().add(DialogBox.createClippyDialog(text, clippyImage));
    }

    @Override
    public void prettyPrint(String text) {
        showClippyTextbox(text);
    }
    @Override
    public void systemPrint(String text) {
        showClippyTextbox(text);
    }

    @Override
    public void exit() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
}

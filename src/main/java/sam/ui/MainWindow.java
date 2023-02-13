package sam.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import sam.Sam;

public class MainWindow extends AnchorPane {

    private static final String FXML = "/view/MainWindow.fxml";

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Ui ui;

    public MainWindow(Stage stage, Ui ui) {
        this.ui = ui;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML));
            loader.setController(this);
            loader.setRoot(stage);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogContainer.heightProperty()
                .addListener(observable -> scrollPane.setVvalue(1.0));
    }

    public void addUserDialog(VBox dialog) {
        ImageView avatar = new ImageView(ui.userImage);
        DialogBox dialogBox = DialogBox.getLeftDialog(dialog, avatar);
        dialogContainer.getChildren().add(dialogBox);
    }

    public void addSamDialog(VBox dialog) {
        ImageView avatar = new ImageView(ui.samImage);
        DialogBox dialogBox = DialogBox.getRightDialog(dialog, avatar);
        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Reads the user input and issues a command to Sam.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        VBox userDialog = new VBox(userText);
        addUserDialog(userDialog);

        Sam.getSamInstance().issueCommand(userInput.getText());
        userInput.clear();
    }

    /**
     * Disables the input field and send button
     */
    public void disable() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}

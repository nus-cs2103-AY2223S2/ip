// Referenced @wengYing227 for closing Tigerlily
package tigerlily.gui;

import static java.lang.Thread.sleep;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import tigerlily.Tigerlily;
import tigerlily.Ui;

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

    private Tigerlily tigerlily;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image tigerlilyImage = new Image(this.getClass().getResourceAsStream("/images/tigerlily.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTigerlily(Tigerlily t) {
        tigerlily = t;
    }

    public void showStartUp() {
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(DialogBox.getTigerlilyDialog(ui.showWelcome(), tigerlilyImage));
        userInput.clear();
    }

    public void showExit() {
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getTigerlilyDialog(ui.showBye(), tigerlilyImage));
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = tigerlily.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getTigerlilyDialog(response, tigerlilyImage));
        userInput.clear();

        if(tigerlily.isBye()) {
            showExit();
            sleep(500);
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}
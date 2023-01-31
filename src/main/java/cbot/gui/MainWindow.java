package cbot.gui;

import cbot.Cbot;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private Cbot cbot;

    private final Image USER_IMG = new Image(this.getClass().getResourceAsStream("/images/MyUser.png"));
    private final Image CBOT_IMG = new Image(this.getClass().getResourceAsStream("/images/MyCbot.png"));
    private final Image CBOT_BAD_IMG = new Image(this.getClass().getResourceAsStream("/images/MyCbotBad.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCbot(Cbot c) {
        cbot = c;
    }

    public void greetUser() {
        dialogContainer.getChildren().add(DialogBox.getCbotDialog(Cbot.sayHi(), CBOT_IMG));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMG),
                DialogBox.getCbotDialog(response, cbot.isBad() ? CBOT_BAD_IMG : CBOT_IMG)
        );
        userInput.clear();

        if (cbot.isBye()) {
            Platform.exit();
        }
    }
}
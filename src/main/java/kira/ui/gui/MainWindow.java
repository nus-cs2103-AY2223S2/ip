package kira.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import kira.KiraBot;

/**
 * Controller for KiraBot GUI. Provides the layout for the other controls.
 */
public class MainWindow extends GridPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private KiraBot kiraBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image kiraBotImage = new Image(this.getClass().getResourceAsStream("/images/KiraBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBot(KiraBot k) {
        this.kiraBot = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kiraBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKiraDialog(response, kiraBotImage)
        );
        userInput.clear();
    }

    public void setup() {
        String output = kiraBot.setup();
        String startMsg = kiraBot.startMsg();
        dialogContainer.getChildren().add(
                DialogBox.getKiraDialog(startMsg, kiraBotImage)
        );

        if (output != null) {
            dialogContainer.getChildren().add(
                DialogBox.getKiraDialog(output, kiraBotImage)
            );
        }
    }

}

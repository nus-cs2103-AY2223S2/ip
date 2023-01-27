package gui;

import java.util.Objects;

import duke.Duke;
import duke.views.UI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Controller for gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Label appLabel;
    @FXML
    private Button sendButton;
    @FXML
    private VBox modalContainer;
    @FXML
    private Button helpButton;

    private Duke duke;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Jinx.png")));
    private final Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Silco.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        validateInput();
        appLabel.setText("Duke");
        userInput.setFont(new Font("Courier New", 15));
        appLabel.setFont(new Font("Courier New", 30));
        sendButton.setFont(new Font("Courier New", 15));
        modalContainer.getChildren().add(DialogBox.getDukeDialog(UI.helpMessage(), dukeImage));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(UI.welcomeMessage(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void validateInput() {
        sendButton.setDisable(userInput.getText().length() == 0);
    }

    @FXML
    private void toggleModal() {
        modalContainer.setVisible(!modalContainer.isVisible());
        if (modalContainer.isVisible()) {
            dialogContainer.setOpacity(0.3);
        } else {
            dialogContainer.setOpacity(1);
        }
    }

    @FXML
    private void closeModal() {
        modalContainer.setVisible(false);
        dialogContainer.setOpacity(1);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        System.out.println(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        userInput.requestFocus();
    }
}

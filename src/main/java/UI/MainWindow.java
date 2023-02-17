package UI;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

import center.Skylar;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Skylar skylar;

    private Image userPic = new javafx.scene.image.Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image skylarPic = new Image(this.getClass().getResourceAsStream("/images/skyler.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getSkylarDialog(Ui.startMessage(), skylarPic));
    }

    public void setSkylar(Skylar d) {
        skylar = d;

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Skylar's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialogBox = DialogBox.getUserDialog(input, userPic);;
        String response = skylar.getResponse(input);
        if (Ui.isError(response)) {
            userDialogBox.setErrorAppearance();
        }
        DialogBox skylarDialogBox = DialogBox.getSkylarDialog(response, skylarPic);
        dialogContainer.getChildren().addAll(
                userDialogBox,
                skylarDialogBox
        );
        userInput.clear();
        if (Objects.equals(response, Ui.close())) {
            skylar.store();
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event ->  Platform.exit());
            delay.play();
        }
    }
}

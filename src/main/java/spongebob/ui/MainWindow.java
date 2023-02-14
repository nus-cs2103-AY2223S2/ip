package spongebob.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spongebob.Spongebob;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Sets up the window of the app.
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

    private Spongebob spongebob;
    private final Image USER_IMAGE = new Image(
            this.getClass().getResourceAsStream("/images/patrick.png"));
    private final Image SPONGEBOB_IMAGE = new Image(
            this.getClass().getResourceAsStream("/images/SpongeBob.jpg"));

    /**
     * Initializes the scene.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //scrollPane.setFitToHeight(true);
    }

    public void setSpongebob(Spongebob d) {
        assert d != null : "Spongebob hasn't been created.";
        spongebob = d;
        dialogContainer.getChildren().add(
                SpongebobDialogBox.getSpongebobDialog(d.getGreetingMsg(), SPONGEBOB_IMAGE));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sponge-bob's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = spongebob.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialogBox(input, USER_IMAGE),
                SpongebobDialogBox.getSpongebobDialog(response, SPONGEBOB_IMAGE)
        );
        userInput.clear();

        if (input.trim().equals("exit")) {
            logout();
        }
    }

    private static void logout() {
        // adapted from
        // https://stackoverflow.com/questions/2258066/run-a-java-function-after-a-specific-number-of-seconds
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.exit();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000L);
    }
}


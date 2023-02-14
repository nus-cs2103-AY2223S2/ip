package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;


public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image DUKE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        assert d != null : "Duke hasn't been created.";
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(d.getGreetingMsg(), DUKE_IMAGE));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDukeDialog(response, DUKE_IMAGE)
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


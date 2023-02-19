package jarvis.frontend;

import jarvis.backend.Jarvis;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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

    private Jarvis jarvis;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
//        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(jarvis.getResponse("welcome"), dukeImage));
    }

    public void setJarvis(Jarvis j) {
        jarvis = j;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(jarvis.getResponse("welcome"), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("")) {
            return;
        }
        String response = jarvis.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog((input), userImage),
                DialogBox.getDukeDialog((response), dukeImage)
        );
        userInput.clear();

        if (input.strip().equals("bye")) {
            Timer delay = new Timer();
            TimerTask exit = new TimerTask() {
                @Override
                public void run() {
                    Main.exit();
                }
            };
            //  Wait 2s before closing.
            delay.schedule(exit, 2000);
        }
    }
}

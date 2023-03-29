package chad.frontend;

import chad.backend.Chad;
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

    private Chad chad;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/soyjak.png"));
    private final Image jarvisImage
            = new Image(this.getClass().getResourceAsStream("/images/chad.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChad(Chad j) {
        chad = j;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(chad.getResponse("welcome"), jarvisImage
        ));
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
        String response = chad.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog((input), userImage),
                DialogBox.getDukeDialog((response), jarvisImage
                )
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
            delay.schedule(exit, 1500);
        }
    }
}

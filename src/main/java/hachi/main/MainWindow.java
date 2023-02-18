package hachi.main;

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

    private Hachi hachi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bee.png"));
    private Image hachiImage = new Image(this.getClass().getResourceAsStream("/images/hachi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void showWelcome() {
        String welcome = hachi.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getHachiDialog(welcome, hachiImage)
        );
        userInput.clear();
    }

    public void setHachi(Hachi h) {
<<<<<<< HEAD
        assert h != null;
=======
>>>>>>> master
        hachi = h;
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hachi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHachiDialog(response, hachiImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 800L);
        }
    }
}
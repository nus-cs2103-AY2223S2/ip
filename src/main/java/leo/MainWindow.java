package leo;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls. Adapted from: https://github.com/JustinPeng13/ip/ and https://se-education.org/guides/tutorials/javaFxPart2.html
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

    private Leo leo;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/wc.jpg"));
    private Image leoImage = new Image(this.getClass().getResourceAsStream("/images/leo.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getLeoDialog("Hello! I'm Leo\nWhat can I do for you?", leoImage)
        );
    }

    @FXML
    public void handleUserInput() {
        String request = userInput.getText();

        if (request.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> Platform.exit() );
            delay.play();
        }

        String response = leo.getResponse(request);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(request, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();


    }


}

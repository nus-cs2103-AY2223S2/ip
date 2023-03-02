package chad.ui;

import chad.Chad;
import chad.command.ExitCommand;
import chad.parser.Parser;
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

    private Image bot = new Image(this.getClass().getResourceAsStream("/images/meme.jpg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/simpson.jpg"));

    /**
     * Initialise scroll pane and display welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getChadDialog(UI.showWelcomeMsg(), user));
    }

    public void setChad(Chad c) {
        this.chad = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's response after performing
     * desired task and then appends them to the dialog container.
     * Clears the user input after processing and display.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = chad.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, bot),
                DialogBox.getChadDialog(dukeText, user)
        );
        userInput.clear();
        checkIsClosePlatform(userText);
    }

    private void checkIsClosePlatform(String input) {
        Parser parser = new Parser(input);

        if (parser.processRequest() instanceof ExitCommand) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}

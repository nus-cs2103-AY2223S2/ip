package duke.gui;

import duke.Duke;
import duke.ui.Ui;
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

    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/kanye.png"));
    private Image warningImage = new Image(this.getClass().getResourceAsStream("/images/screaming.png"));
    private Image passiveImage = new Image(this.getClass().getResourceAsStream("/images/grandma.png"));

    /**
     * Initializes the scroll pane and dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.welcomeMessage(), passiveImage)
        );
    }


    /**
     * Sets the current main class
     *
     * @param d current Duke class
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String dukeResponse = duke.initialise(input);
        Image dukeImage = duke.isNotAnInstruction() ? warningImage : passiveImage;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(dukeResponse, dukeImage)
        );
        userInput.clear();

        if (duke.isExitApp()) {
            PauseTransition exit = new PauseTransition(Duration.seconds(2));
            exit.setOnFinished(e -> Platform.exit());
            exit.play();
        }
    }
}

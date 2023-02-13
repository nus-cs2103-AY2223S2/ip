package gui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String buttonDefaultStyle = "-fx-padding: 5 20 5 20;"
            + "-fx-background-color: transparent;"
            + "-fx-border-color: black;"
            + "-fx-border-radius: 10";

    private static final String buttonHoverStyle = "-fx-padding: 5 20 5 20;"
            + "-fx-background-color: #7B8FA1;"
            + "-fx-background-radius: 10;"
            + "-fx-border-color: black;"
            + "-fx-border-radius: 10";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Stage stage;

    /**
     * Initializes the MainWindow of the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initializeButton();
    }

    /**
     * Set an image as the background for the send button. Also add borders and background colors.
     */
    public void initializeButton() {
        Image buttonImage = new Image(this.getClass().getResourceAsStream("/images/DaButton.png"),
                16, 16, false, true);
        sendButton.setGraphic(new ImageView(buttonImage));
        sendButton.setStyle(buttonDefaultStyle);
    }

    /**
     * Displays a greeting message to the user on startup.
     */
    public void displayInitGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getDukeInitMessage(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one is user input and the other is duke. Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }

    @FXML
    private void darkenButton() {
        sendButton.setStyle(buttonHoverStyle);
    }

    @FXML
    private void lightenButton() {
        sendButton.setStyle(buttonDefaultStyle);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

package gui;

import Nerd.Nerd;
import Nerd.Ui.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Nerd nerd;
    private Stage stage;
    private Ui ui;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/amoonguss.png"));
    private Image nerdImage = new Image(this.getClass().getResourceAsStream("/images/nerd.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNerd(Nerd d, Ui ui) {
        nerd = d;
        this.ui = ui;
    }

    public void setDefaultMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.printSuccessfulConnection(), nerdImage),
                DialogBox.getDukeDialog(ui.showWelcome(), nerdImage),
                DialogBox.getDukeDialog(ui.showCommandList(), nerdImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nerd.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, nerdImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> stage.close() );
            delay.play();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
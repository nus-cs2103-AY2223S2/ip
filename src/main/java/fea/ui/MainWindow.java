package fea.ui;

import fea.Fea;
import fea.commands.Command;
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

    private Fea fea;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/master.jpg"));
    private Image feaImage = new Image(this.getClass().getResourceAsStream("/images/mash.jpg"));

    /**
     * Initialise the main window for the FEA GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getFeaDialog(Ui.WELCOME, feaImage));
    }

    public void setFea(Fea fea) {
        this.fea = fea;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * FEA's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = fea.parseInput(input);
        String response = fea.getResponse(command);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFeaDialog(response, feaImage));
        userInput.clear();
        if (command.isExit()) {
            // adapted from https://stackoverflow.com/questions/54229373/
            // why-thread-sleep-doesnt-work-accordingly-in-javafx
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                stage.close();
            });
            delay.play();
        }
    }
}

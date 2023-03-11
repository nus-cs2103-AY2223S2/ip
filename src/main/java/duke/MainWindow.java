package duke;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Cap.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/IronMan.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getInstruction(), dukeImage));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.loadDuke(), dukeImage));

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.strip().equals("bye")) {
            String byeMessage = duke.exitDuke();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(byeMessage, dukeImage)
            );
            userInput.clear();
            PauseTransition pt = new PauseTransition(Duration.seconds(3));
            pt.setOnFinished(e -> {
                Platform.exit();
            });
            pt.play();

        } else {
            String response = duke.runDuke(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

}
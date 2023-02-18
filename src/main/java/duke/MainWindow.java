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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pooh.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/tiger.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(d.getInitMsg(), dukeImage)
       );
    }

    @FXML
    private void handleUserInput() throws Exception{
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            if (input.equalsIgnoreCase("bye")) {
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> Platform.exit());
                pause.play();
            }
            userInput.clear();
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMsg(), dukeImage)
            );
        }
    }


}

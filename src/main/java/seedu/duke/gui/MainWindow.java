package seedu.duke.gui;


import seedu.duke.Duke;
import seedu.duke.Ui;

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

import java.util.Objects;


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

    private final Ui ui = new Ui();

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(this.ui.sayGreetings(), dukeImage)
        );
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage));
        say(response);
        userInput.clear();
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        if (input.equals("bye")) {
            handleBye();
        }
    }

    /**
     * Idea of splitting into two dialogue boxes for long replies from @mynameizzhafeez
     * @param message message to be shown
     */
    public void say(String message) {
        String[] lines = message.split("\n");
        int i = 0;
        StringBuilder output = new StringBuilder();
        while (true) {
            output.append(lines[i]).append("\n");
            i++;
            if (i == lines.length) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(output.toString(), dukeImage)
                );
                break;
            }
            if (i % 5 == 0) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(output.toString(), dukeImage)
                );
                output = new StringBuilder();
            }
        }
    }

    private void handleBye() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}

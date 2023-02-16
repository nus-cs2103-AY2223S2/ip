package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.Group;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
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

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke Avatar.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Receiver Avatar.jpeg"));

    @FXML
    public void initialize() throws IOException {
//        duke = new Duke("data/tasks.txt");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image img = new Image(this.getClass().getResourceAsStream("/images/Background Image.jpeg"));
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        dialogContainer.setBackground(bGround);
        // dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.run(), dukeImage, "#d4fffd")); //syntax
    }

    public void setWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.run(), dukeImage, "#d4fffd"));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException, FileNotFoundException, ParseException, InterruptedException {
        String input = userInput.getText();
        String response = duke.feedingIntoInterface(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage, "#eca3b3"),
                new Group(DialogBox.getDukeDialog(response, dukeImage, "#d4fffd")));
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}

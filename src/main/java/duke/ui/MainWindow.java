package duke.ui;

import duke.Duke;
import duke.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image panpanImage = new Image(this.getClass().getResourceAsStream("/images/panpan.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String inputString = userInput.getText();
        Label input = new Label(inputString);
        Label response = new Label (duke.getResponse(inputString));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, new ImageView(userImage)),
                DialogBox.getDukeDialog(response, new ImageView(panpanImage))
        );

        if (inputString.toLowerCase().equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}

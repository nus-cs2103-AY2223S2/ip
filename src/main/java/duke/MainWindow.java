package duke;

import duke.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileNotFoundException;

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
    private Main main;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Image background = new Image(this.getClass().getResourceAsStream("/images/ChatBackground.png"));

    @FXML
    public void initialize() {
        BackgroundImage bg = new BackgroundImage(background,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        dialogContainer.setBackground(new Background(bg));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Main main, Duke d) {

        assert main != null: "Main does not exists in MainWindow";
        assert d != null: "Duke does not exists in MainWindow";

        this.main = main;
        this.duke = d;
        this.duke.setMainWindow(this);
    }

    /**
     * Creates one dialog box echoing user input reflecting and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws FileNotFoundException {
        String input = userInput.getText();

        if (!input.trim().isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );

            userInput.clear();
            duke.runInput(input);

        }
    }

    /**
     * Creates one dialog box containing Duke's reply and then appends them to
     * the dialog container.
     */
    public void passDukeResponse(String response) {
        if (!response.trim().isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
    }

    public void passNotesToUserInput (String notes) {
        userInput.setText(notes);
    }
}
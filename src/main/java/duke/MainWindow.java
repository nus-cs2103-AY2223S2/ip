package duke;

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
        this.main = main;
        this.duke = d;
        this.duke.setMainWindow(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    @FXML
//    private void handleUserInput() {
//        String input = userInput.getText();
//        String response = duke.getResponse(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(input, userImage),
//                DialogBox.getDukeDialog(response, dukeImage)
//        );
//        userInput.clear();
//    }

    @FXML
    private void handleUserInput() throws FileNotFoundException {
        String input = userInput.getText();
//        String response = duke.getResponse(input);

        if (!input.trim().isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
                    //                ,
                    //                DialogBox.getDukeDialog(response, dukeImage)
            );

            duke.runInput(input);
            userInput.clear();
        }
    }

    public void passDukeResponse(String response) {
        if (!response.trim().isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
    }
}
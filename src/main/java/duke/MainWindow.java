package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    // userImage taken from "https://www.pngfind.com/mpng/TihxTxw_asta-black-clover-black-clover-asta-png-transparent/"
    // dukeImage taken from "https://blackcloverthots.tumblr.com/post/612892427599134720/marisashinx-nero-gets-what-nero-deserves"
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Asta.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/New.png"));

    @FXML
    public void initialize() {
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.printGreetingMessage(), dukeImage));
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
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

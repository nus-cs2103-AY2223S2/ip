package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/shenganning.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/trex.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/bridge.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        /*
        BackgroundSize bkgSize = new BackgroundSize(552, 388, true, true, false, true);
        BackgroundImage bkgImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, null,
                BackgroundPosition.CENTER, bkgSize);
        Background bkg = new Background(bkgImg);
        dialogContainer.setBackground(bkg);*/
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

    /**
     * Sends the greeting message in the GUI to the user.
     */
    public void hello() {
        String greeting = "Hello! I'm T-Rex. Roarrrrrrrrrrrrrr!\nWhat do you need from me?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage));
    }
}

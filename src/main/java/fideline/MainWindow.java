package fideline;

import javafx.application.Platform;
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

    private Fideline fideline;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserPic.png"));
    private Image fidelineNormalImage = new Image(this.getClass().getResourceAsStream("/images/NormalFi.png"));
    private Image fidelineErrorImage = new Image(this.getClass().getResourceAsStream("/images/ErrorFi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setFideline(Fideline f) {
        fideline = f;
        dialogContainer.getChildren().addAll(
                DialogBox.getFidelineDialog(f.getIntroduction(), fidelineNormalImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = fideline.getResponse(input);
        if (fideline.isTerminated()) {
            Platform.exit();
        }

        if (!fideline.isLastCommandValid()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getFidelineDialog(response, fidelineErrorImage));
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getFidelineDialog(response, fidelineNormalImage));
        }
        userInput.clear();
    }

}

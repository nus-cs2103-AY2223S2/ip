import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    public AnchorPane myAnchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private UWUTaskmaster taskmaster;

    InputStream userImageStream = this.getClass().getResourceAsStream("/images/profile.jpg");
    InputStream taskMasterStream = this.getClass().getResourceAsStream("/images/uwu.jpg");
    Image userImage = new Image(userImageStream);
    Image taskMasterImage = new Image(taskMasterStream);


    @FXML
    public void initialize() {
        assert userImageStream != null;
        assert taskMasterStream != null;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the TaskMaster object to use for the application.
     *
     * @param d the TaskMaster object to use
     */
    public void setTaskMaster(UWUTaskmaster d) {
        taskmaster = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskmaster.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskmasterDialog(response, taskMasterImage)
        );
        userInput.clear();
    }
}
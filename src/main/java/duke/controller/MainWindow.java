package duke.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// import duke.driver.GuiDriver;
// import duke.storage.Storage;
// import duke.tasks.TaskList;


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

    private Image userImage =
            new Image(getClass().getResourceAsStream("/resources/images/Duke.png"));
    private Image dukeImage =
            new Image(getClass().getResourceAsStream("/resources/images/Duke.png"));


    public MainWindow() {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource("/resources/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = input;
        DialogBox userBox = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeBox = DialogBox.getDukeDialog(response, dukeImage);

        dialogContainer.getChildren().addAll(userBox, dukeBox);
        userInput.clear();
    }
}

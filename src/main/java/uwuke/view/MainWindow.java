package uwuke.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uwuke.UwUke;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Printer.setDialogContainer(dialogContainer);
    }

    @FXML
    private void handleUserInput() {
        assert userInput != null : "User Input Text Field should have been initialised!";
        String inputString = userInput.getText();
        assert inputString != null : "User input should not be null!";
        DialogBox userBox = DialogBox.getUserDialogBox(inputString);
        assert dialogContainer != null : "Dialog Container should be initialised!";
        dialogContainer.getChildren().add(userBox);
        UwUke.displayDukeResponse(inputString);
        userInput.clear();
    }
}
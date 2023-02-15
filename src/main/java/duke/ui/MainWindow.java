package duke.ui;

import duke.Duke;
import duke.TaskException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for UI
 */
public class MainWindow extends AnchorPane {
    private Duke duke;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;


    /**
     * Initialize the UI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(new DialogBox("Hello, duke here.", true));
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(new DialogBox(input, false), new DialogBox(response, true));
        } catch (TaskException e) {
            dialogContainer.getChildren().addAll(new DialogBox(input, false), new DialogBox(e.getMessage(), true));
        } finally {
            userInput.clear();
            assert userInput.getText().equals("");
        }
    }
}

package duke.controller;

import duke.Duke;
import duke.util.Stateful;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/duke.png")));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

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
    private void handleUserInput() {
        String input = userInput.getText();
        Stateful response = duke.getResponse(input);
        assert response.getOutputs() != null && response.getOutputs().size() > 0;
        String output = String.join("\n", response.getOutputs());
        dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(output, dukeImage)
                );
        userInput.clear();
        if (response.getState().isDoQuit()) {
            userInput.setEditable(false);
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }
    }
}
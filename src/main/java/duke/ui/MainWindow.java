package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.History;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private History history;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DukeDialogBox.getDukeDialog(new Gui().greet(), dukeImage));
        history = History.getInstance();
        history.addGuiState(dialogContainer.getChildren());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response == null) {
            undoUserInput();
        } else {
            dialogContainer.getChildren().addAll(UserDialogBox.getUserDialog(input, userImage),
                    DukeDialogBox.getDukeDialog(response, dukeImage));
            history.addGuiState(dialogContainer.getChildren());

        }

        userInput.clear();
    }

    private void undoUserInput() {
        try {
            ObservableList<Node> temp = history.undoGuiState();
            boolean works = dialogContainer.getChildren().setAll(temp);
            System.out.println(works);
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(DukeDialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }
    }
}

package duke.view;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private MenuItem loadCsvMenuItem;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/userIcon.jpg")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/dukeIcon.jpg")));

    private static final String LOAD_SUCCESS_OUTPUT = "Tasks loaded successfully!";
    private static final String WELCOME_MESSAGE = "Hello I'm Duke, your personal task manager!";
    private static final String LOAD_ERROR_MESSAGE = "Unable to load tasks from database!";

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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void loadDataFromCsv() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String fileName = chooser.getSelectedFile().getPath();

        boolean isLoadSuccess = this.duke.loadTasks(fileName);
        if (isLoadSuccess) {
            showLoadSuccess();
        }
    }

    private void showLoadSuccess() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(LOAD_SUCCESS_OUTPUT, dukeImage)
        );
    }

    public void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage)
        );
    }

    public void showLoadingErrorMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(LOAD_ERROR_MESSAGE, dukeImage)
        );
    }
}

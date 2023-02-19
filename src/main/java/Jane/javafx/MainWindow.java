package jane.javafx;

import jane.Jane;
import jane.Storage;
import jane.TaskList;
import javafx.fxml.FXML;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImg.png"));
    private final Image janeImage = new Image(this.getClass().getResourceAsStream("/images/Jane.png"));

    private TaskList tasks;

    /**
     * Shows text that will be displayed upon starting Launcher.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        tasks = this.load();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(logo, janeImage)
        );
    }

    public void setJane(Jane j) {
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    private TaskList load() {
        Storage.createDir();
        TaskList tasks = new TaskList(Storage.loadList());
        return tasks;
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tasks.useCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, janeImage)
        );
        userInput.clear();
    }
}

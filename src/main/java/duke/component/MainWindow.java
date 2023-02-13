package duke.component;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TaskList tasks;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    private final Image shockImage = new Image(this.getClass().getResourceAsStream("/images/DaError.jpg"));



    @FXML
    public void setDuke(TaskList tasksList) {
        tasks = tasksList;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.intro(), dukeImage)
        );
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setSpacing(5);
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Just close the app you dummy ~(@o@)~", dukeImage)
            );
            return;
        }
        try {
            String response = Parser.parseRawString(input, tasks);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (IllegalArgumentException exception) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeError(Ui.invalidCommand(), shockImage)
            );
        } catch (IndexOutOfBoundsException exception) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeError(Ui.missingArgs(), shockImage)
            );
        }

    }
}

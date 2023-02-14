package gui;

import duke.Duke;
import duke.task.Task;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {

    @FXML
    private ScrollPane dialogScrollPane;
    @FXML
    private ScrollPane outputScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private VBox outputContainer;
    @FXML
    private TextField userInput;
    private Duke duke;

    @FXML
    public void initialize() {
        dialogScrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        outputScrollPane.vvalueProperty().bind(this.outputContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public void run() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(this.duke.getGreeting()));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.duke.getResponse(input);
        displayDialog(input, response);
        displayOutput(input);
        userInput.clear();
    }

    private void displayDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response));
    }

    private void displayOutput(String input) {
        String command = input.split(" ", 2)[0].toLowerCase();
        if (command.equals("list") || command.equals("find")) {
            outputContainer.getChildren().clear();
            for (Task task : this.duke.getTasksToDisplay()) {
                outputContainer.getChildren().add(TaskBox.getTaskBox(task));
            }
        }
    }
}

package twofive.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import twofive.TwoFive;

/**
 * Represents the main window of the chatbot GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane commandScrollPane;
    @FXML
    private VBox commandContainer;
    @FXML
    private ScrollPane taskScrollPane;
    @FXML
    private TaskContainer taskContainer;
    @FXML
    private ScrollPane outputScrollPane;
    @FXML
    private Label outputLabel;
    @FXML
    private TextField userInput;

    private TwoFive twoFive;

    /**
     * Initializes the components of the main window.
     */
    @FXML
    public void initialize() {
        commandScrollPane.vvalueProperty().bind(commandContainer.heightProperty());
        outputScrollPane.vvalueProperty().bind(outputLabel.heightProperty());
    }

    public void setTwoFive(TwoFive t) {
        twoFive = t;
        taskContainer = new TaskContainer(twoFive.getTaskList());
        taskScrollPane.setContent(taskContainer);
        taskScrollPane.vvalueProperty().bind(taskContainer.heightProperty());
        taskContainer.showTasks();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String twoFiveResponse = twoFive.handleUserInput(input);
        commandContainer.getChildren().add(new Label(input));
        outputLabel.setText(twoFiveResponse);
        taskContainer.showTasks();
        userInput.clear();
    }
}

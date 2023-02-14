package twofive.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        outputScrollPane.vvalueProperty().bind(outputLabel.heightProperty());
        outputLabel.prefWidthProperty().bind(outputScrollPane.widthProperty());
        commandContainer.prefWidthProperty().bind(commandScrollPane.widthProperty());
    }

    public void setTwoFive(TwoFive t) {
        twoFive = t;
        taskContainer = new TaskContainer(twoFive.getTaskList());
        taskScrollPane.setContent(taskContainer);
        taskScrollPane.vvalueProperty().bind(taskContainer.heightProperty());
        taskContainer.prefWidthProperty().bind(taskScrollPane.widthProperty());
        taskContainer.showTasks();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] twoFiveResponse = twoFive.handleUserInput(input);
        String currDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String labelColor = twoFiveResponse[1];

        Label commandLabel = new Label(currDateTime + ": " + input);
        commandLabel.setFont(Font.font(15));
        commandLabel.setWrapText(true);
        commandLabel.prefWidthProperty().bind(commandScrollPane.widthProperty());
        commandLabel.setStyle(labelColor);

        commandContainer.getChildren().add(0, commandLabel);
        outputLabel.setText(twoFiveResponse[0]);
        outputLabel.setStyle(labelColor);
        taskContainer.showTasks();
        userInput.clear();
    }
}

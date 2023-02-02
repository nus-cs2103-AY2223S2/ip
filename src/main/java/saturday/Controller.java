package saturday;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import saturday.collections.TaskList;
import saturday.models.Task;

import java.util.Collection;

public class Controller {
    @FXML
    private TextField commandField;
    @FXML
    private VBox taskListContainer;
    @FXML
    private Label responseField;
    @FXML
    private Button exitButton;

    private Saturday saturday;

    @FXML
    public void initialize() {

    }

    public void setSaturday(Saturday saturday) {
        this.saturday = saturday;
        updateTaskList();
    }

    private void updateTaskList() {
        TaskList taskList = saturday.getTaskList();
        int size = taskList.size();
        System.out.println(size);

        try {
            taskListContainer.getChildren().clear();
            for (Task task : taskList) {
                TaskView t = TaskView.getTaskView(task);
                taskListContainer.getChildren().add(t);
                System.out.println("yo");
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    @FXML
    private void handleCommand() {
        String input = commandField.getText();
        String response = saturday.getResponse(input);
        updateTaskList();
        responseField.setText(response);
        commandField.clear();
    }

    @FXML
    private void handleExitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}

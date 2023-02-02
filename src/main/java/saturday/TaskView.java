package saturday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import saturday.models.Task;

import java.io.IOException;

public class TaskView extends BorderPane {
    @FXML
    private Label description;
    private Task task;

    private TaskView(Task task) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("/view/Task.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        description.setText(task.getDescription());
        this.task = task;
    }

    public static TaskView getTaskView(Task task) {
        return new TaskView(task);
    }
}

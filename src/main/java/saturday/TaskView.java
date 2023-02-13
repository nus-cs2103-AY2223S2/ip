package saturday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import saturday.exceptions.SaturdayException;
import saturday.models.Deadline;
import saturday.models.Event;
import saturday.models.Task;
import saturday.models.ToDo;

import java.io.IOException;

public class TaskView extends BorderPane {
    @FXML
    private Label index;
    @FXML
    private Label description;
    @FXML
    private Button taskType;
    @FXML
    private Button timeFrame;
    @FXML
    private Button isDone;
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

        // set index
        index.setText(String.valueOf(task.getIndex()));

        // set description
        description.setText(task.getDescription());

        // set isDone
        if (task.getIsDone()) {
            isDone.setText("Complete");
            isDone.setStyle("-fx-background-color: #dbd8e3; -fx-background-radius: 1em");
        } else {
            isDone.setText("In Progress");
        }

        // set taskType and timeFrame
        if (task instanceof Event) {
            taskType.setText("Event");
            taskType.setStyle("-fx-background-color: #4285F4; -fx-background-radius: 1em");
            timeFrame.setText(task.toString());
        } else if (task instanceof Deadline) {
            taskType.setText("Deadline");
            taskType.setStyle("-fx-background-color: #DB4437; -fx-background-radius: 1em");
            timeFrame.setText(task.toString());
        } else if (task instanceof ToDo) {
            taskType.setText("To Do");
            taskType.setStyle("-fx-background-color: #F4B400; -fx-background-radius: 1em");
            timeFrame.setText(" - ");
        } else {
            throw new SaturdayException("Unknown task type");
        }

        // set task
        this.task = task;
    }

    public static TaskView getTaskView(Task task) {
        return new TaskView(task);
    }
}

package twofive.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a box containing details of a task, such as its index, description
 * type, completion status and other details.
 */
public class TaskBox extends HBox {
    private static final String TASK_COMPLETED_STYLE = "-fx-background-color: #90EE90; -fx-background-radius: 10";
    private static final String TASK_UNCOMPLETED_STYLE = "-fx-background-color: #E55451; -fx-background-radius: 10";
    @FXML
    private Label taskNumber;
    @FXML
    private Label taskDescription;
    @FXML
    private Label taskType;
    @FXML
    private Label taskStatus;
    @FXML
    private Label taskInfo1;
    @FXML
    private Label taskInfo2;

    private TaskBox(String ... taskDetails) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TaskBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String taskNumberString = taskDetails[0];
        String taskTypeString = taskDetails[1];
        String taskStatusString = taskDetails[2];
        boolean isCompleted = parseTaskStatus(taskStatusString);

        taskNumber.setText(taskNumberString + ".");
        taskType.setText(taskTypeString);
        taskStatus.setText(taskStatusString);
        taskDescription.setText(taskDetails[3]);

        if (taskTypeString.equals("Deadline")) {
            taskInfo1.setText("Deadline: " + taskDetails[4]);
            taskInfo1.setVisible(true);
        } else if (taskTypeString.equals("Event")) {
            taskInfo1.setText("From: " + taskDetails[4]);
            taskInfo2.setText("To: " + taskDetails[5]);
            taskInfo1.setVisible(true);
            taskInfo2.setVisible(true);
        }

        setTaskLabelColor(isCompleted);

        setTaskBoxColor(Integer.parseInt(taskNumberString));
    }

    /**
     * Returns the boolean representing the completion status of the task given a String.
     *
     * @param taskStatusString String representing the completion status of the task.
     * @return A boolean representing the completion status of the task.
     */
    private boolean parseTaskStatus(String taskStatusString) {
        return taskStatusString.equals("Completed");
    }

    /**
     * Sets the color of a task label in the list according to its completion status.
     *
     * @param isTaskCompleted Boolean representing the completion status of the task.
     */
    private void setTaskLabelColor(boolean isTaskCompleted) {
        if (isTaskCompleted) {
            this.taskStatus.setStyle(TASK_COMPLETED_STYLE);
        } else {
            this.taskStatus.setStyle(TASK_UNCOMPLETED_STYLE);
        }
    }

    /**
     * Sets the color of a task box according to its index.
     *
     * @param taskNum The index of the task.
     */
    private void setTaskBoxColor(int taskNum) {
        if (taskNum % 2 == 0) {
            this.setStyle("-fx-background-color: #EBF4FA;");
        }
    }

    public static TaskBox getTaskBox(String ... taskDetails) {
        return new TaskBox(taskDetails);
    }
}

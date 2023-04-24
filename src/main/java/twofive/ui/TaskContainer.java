package twofive.ui;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import twofive.task.Task;

/**
 * Represents a list of tasks to be displayed to the user.
 */
public class TaskContainer extends VBox {
    private static ArrayList<Task> tasks;

    /**
     * Represents a container for a list of tasks.
     *
     * @param t ArrayList containing list of tasks to be shown.
     */
    public TaskContainer(ArrayList<Task> t) {
        tasks = t;
        this.setStyle("-fx-background-color: #FFFFFF;");
    }

    public static void setTasks(ArrayList<Task> t) {
        tasks = t;
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void showTasks() {
        this.getChildren().clear();
        assert this.getChildren().size() == 0 : "All tasks shown should have been cleared";
        for (int i = 0; i < tasks.size(); i++) {
            ArrayList<String> taskDetails = tasks.get(i).getTaskDetails();
            taskDetails.add(0, Integer.toString(i + 1));
            TaskBox newTaskBox = TaskBox.getTaskBox(taskDetails.toArray(new String[0]));
            newTaskBox.prefWidthProperty().bind(this.widthProperty());
            this.getChildren().add(newTaskBox);
        }
    }
}

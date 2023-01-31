package twofive.ui;

import javafx.scene.layout.VBox;
import twofive.task.Task;

import java.util.ArrayList;

public class TaskContainer extends VBox {
    private static ArrayList<Task> tasks;

    public TaskContainer(ArrayList<Task> t) {
        tasks = t;
        this.setStyle("-fx-background-color: #FFFFFF;");
    }

    public static void setTasks(ArrayList<Task> t) {
        tasks = t;
    }

    public void showTasks() {
        this.getChildren().clear();
        for (int i = 0; i < tasks.size(); i++) {
            ArrayList<String> taskDetails = tasks.get(i).getTaskDetails();
            taskDetails.add(0, Integer.toString(i + 1));
            this.getChildren().add(TaskBox.getTaskBox(taskDetails.toArray(new String[0])));
        }
    }
}

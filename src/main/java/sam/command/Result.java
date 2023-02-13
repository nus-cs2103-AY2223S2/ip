package sam.command;

import java.util.List;
import java.util.stream.Stream;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sam.task.Task;
import sam.ui.TaskCard;

public class Result {
    VBox result;

    public Result() {
        result = new VBox();
    }

    public VBox getResult() {
        return result;
    }

    public void addMessage(String message) {
        Label label = new Label(message);
        result.getChildren().add(label);
    }

    public void addMessages(String... messages) {
        Label[] labels = (Label[]) Stream.of(messages).map(m -> new Label(m)).toArray();
        result.getChildren().addAll(labels);
    }

    public void addTask(Task task) {
        addTask(task, 0);
    }

    public void addTask(Task task, int id) {
        TaskCard card = new TaskCard(task, id);
        result.getChildren().add(card);
    }

    public void addTaskList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) {
                addTask(task, i);
            }
        }
    }
}

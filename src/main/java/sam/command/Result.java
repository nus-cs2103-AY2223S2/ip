package sam.command;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sam.task.Task;
import sam.ui.TaskCard;

public class Result {
    List<Node> result;

    public Result() {
        result = new ArrayList<>();
    }

    public Node[] getResult() {
        return result.toArray(new Node[0]);
    }

    public void addMessage(String message) {
        Label label = new Label(message);
        result.add(label);
    }

    public void addMessages(String... messages) {
        for (String message : messages) {
            addMessage(message);
        }
    }

    public void addTask(Task task) {
        addTask(task, 0);
    }

    public void addTask(Task task, int id) {
        TaskCard card = new TaskCard(task, id);
        result.add(card);
    }

    public void addTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) {
                addTask(task, i + 1);
            }
        }
    }
}

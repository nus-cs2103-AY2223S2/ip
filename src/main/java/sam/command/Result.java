package sam.command;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sam.task.Task;
import sam.ui.TaskCard;

/**
 * Represents the result of a command.
 */
public class Result {
    private List<Node> result;

    public Result() {
        result = new ArrayList<>();
    }

    public Node[] getResult() {
        return result.toArray(new Node[0]);
    }

    /**
     * Adds a string label to the result.
     *
     * @param message The string to add.
     */
    public void addMessage(String message) {
        Label label = new Label(message);
        result.add(label);
    }

    /**
     * Adds a list of string labels to the result.
     *
     * @param messages A list of strings to add.
     */
    public void addMessages(String... messages) {
        for (String message : messages) {
            addMessage(message);
        }
    }

    /**
     * Adds a task card to the result without an id.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        addTask(task, 0);
    }

    /**
     * Adds a task card to the result with the given id.
     *
     * @param task The task to add.
     * @param id The id of the task.
     */
    public void addTask(Task task, int id) {
        TaskCard card = new TaskCard(task, id);
        result.add(card);
    }

    /**
     * Adds a list of tasks to the result.
     *
     * @param tasks The list of tasks to add.
     */
    public void addTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) {
                addTask(task, i + 1);
            }
        }
    }
}

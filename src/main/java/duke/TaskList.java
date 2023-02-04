package duke;

import duke.Commands.Tasks.Task;
import java.util.ArrayList;

/**
 * This class serves to represent a to-do list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list
     *
     * @param newTask The task to be added to the list
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Removes a task from the list
     *
     * @param index The index to be removed
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Shows the size of the list
     *
     * @return The size of the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the list
     *
     * @param index The index of the task to be retrieved
     * @return The retrieved task
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        return res;
    }
}

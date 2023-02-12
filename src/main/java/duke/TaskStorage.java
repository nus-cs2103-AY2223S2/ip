package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Analogous to the TaskList class suggested by the 2103T module coordinators.
 * Which is encapsulates a List which stores Task objects.
 */
public class TaskStorage {
    private List<Task> taskStorage;
    // Tasks are indexed from 0 in taskstorage.
    private int ind = 0; //Number of Current Tasks

    /**
     * Constructs an empty TaskStorage object.
     */
    public TaskStorage() {
        taskStorage = new ArrayList<Task>();
    }

    /**
     * Adds a task to the TaskStorage object with printing effects.
     * @param t The task.
     */
    public String addTask(Task t) {
        StringBuilder chunkOfText = new StringBuilder();
        chunkOfText.append("Got it. I've added this task:\n");
        chunkOfText.append(" " + t);
        chunkOfText.append('\n');
        this.taskStorage.add(t);
        this.ind++;
        chunkOfText.append("Now you have " + this.ind + " task(s) in the list.\n");
        return chunkOfText.toString();
    }

    /**
     * Adds a task to the TaskStorage object without printing effects.
     *
     * @param t The task.
     */
    public void addTaskWithoutPrinting(Task t) {
        this.taskStorage.add(t);
        this.ind++;
    }

    /**
     * Return the number of tasks in the taskStorage object.
     *
     * @return The number of tasks in the taskStorage object.
     */
    public int noTasks() {
        return ind;
    }

    /**
     * Returns the task based on the given index in the taskStorage object.
     *
     * @param i The given index.
     * @return The task at the given index.
     */
    public Task getTask(int i) {
        return this.taskStorage.get(i);
    }

    /**
     * Deletes the given task in the taskStorage object.
     *
     * @param t The given task.
     */
    public String deleteTask(Task t) {
        StringBuilder chunkOfText = new StringBuilder();
        chunkOfText.append("Noted. I've removed this task:\n");
        chunkOfText.append("  " + t + "\n");
        this.taskStorage.remove(t);
        ind--;
        chunkOfText.append("Now you have " + ind + " task(s) in the list.\n");
        return chunkOfText.toString();
    }

    /**
     * Prints out the list of tasks in the taskStorage object.
     */
    public String listTask() {
        StringBuilder chunkOfText = new StringBuilder();
        chunkOfText.append("Tasks:");
        chunkOfText.append("\n");
        for (int i = 0; i < this.noTasks(); i++) {
            chunkOfText.append((i + 1) + ".");
            chunkOfText.append(this.getTask(i).toString());
            chunkOfText.append("\n");
        }
        return chunkOfText.toString();
    }
}

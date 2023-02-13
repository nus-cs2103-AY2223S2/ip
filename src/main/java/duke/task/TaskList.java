package duke.task;
import duke.dukeexceptions.DukeException;
import duke.functions.Storage;

import java.util.ArrayList;

/**
 * A class containing the Tasks current in the checklist.
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructor of TaskList.
     *
     * @param storage which contains the path of the data to store.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            this.tasks = new ArrayList<Task>();
        }
    }

    /**
     * Constructor of TaskList with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns a Task after deleting it from the index.
     *
     * @param index index of the Task to delete from.
     * @return the deleted Task.
     */
    public Task deleteTask(int index) {
        this.storage.deleteInFile(index);
        return tasks.remove(index);
    }

    /**
     * Returns a Task at a given index.
     *
     * @param index index of the Task to retrieve.
     * @return the Task at an index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a Task to the ArrayList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public String setTaskDone(int index) {
        Task task = this.getTask(index);
        this.storage.changeStatusInFile(index, true);
        return task.setDone();
    }

    public String setTaskNotDone(int index) {
        Task task = this.getTask(index);
        this.storage.changeStatusInFile(index, false);
        return task.setNotDone();
    }

    /**
     * Returns the current Tasks in the checklist.
     *
     * @return a string displaying all the Tasks in the checklist.
     */
    public String printTasks() {
        int len = tasks.size();
        String reply = "";
        for (int i = 0; i < len; i++) {
            reply = reply + (i + 1) + ". " + tasks.get(i).status();
        }
        return reply;
    }

    /**
     * Returns tasks containing the substring.
     *
     * @return a string to look for amongst the tasks.
     */
    public String findTask(String s) {
        int len = tasks.size();
        String reply = "";
        for (int i = 0; i < len; i++) {
            String taskStatus = tasks.get(i).status();
            if (taskStatus.contains(s)) {
                reply = reply + (i + 1) + ". " + taskStatus + "\n";
            }
        }
        return reply;
    }
}

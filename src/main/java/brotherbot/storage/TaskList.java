package brotherbot.storage;

import brotherbot.ui.Ui;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskStorage;

    /**
     * Constructor for creating a TaskList object containing Tasks.
     */
    public TaskList() {
        this.taskStorage =  new ArrayList<>();
    }

    public Task get(int i) {
        return this.taskStorage.get(i);
    }

    /**
     * Adds Task into the list of Tasks.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.taskStorage.add(task);
    }

    /**
     * Removes Task from the list of Tasks.
     *
     * @param i Index of Task to be removed.
     */
    public void remove(int i) {
        this.taskStorage.remove(i);
    }

    /**
     * Marks Task as done from the list of Tasks.
     *
     * @param i Index of Task to be marked as done.
     */
    public void mark(int i) {
        taskStorage.get(i).markAsDone();
    }

    /**
     * Returns number of tasks in list of tasks.
     *
     * @return Size of taskList.
     */
    public int size() {
        return this.taskStorage.size();
    }

    /**
     * Displays the list of Tasks.
     */
    public void display(Ui ui) {
        int i = 0;
        for(Task task: this.taskStorage) {
            ui.toUser((i + 1) + ". " + task.toString());
            i++;
        }

    }

    /**
     * Writes the list of Tasks into existing data file.
     */
    public void write(PrintWriter x) {
        int i = 0;
        for (Task task : this.taskStorage) {
            x.println((i + 1) + ". " + this.taskStorage.get(i));
            i++;
        }
    }

    public String getPrintFormat(int index) {
        return this.taskStorage.get(index).toString();
    }
}

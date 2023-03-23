package brotherbot.storage;

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
    public String display() {
        String output = null;
        int i = 0;
        for(Task task: this.taskStorage) {
            output = "Here are your existing tasks!\n" + (i + 1) + ". " + task.toString();
            i++;
        }
        if (this.taskStorage.size() == 0) {
            output = "You have 0 Existing Tasks.. cannot la brother, start adding now! Faster!";
        }
        return output;

    }

    /**
     * Writes the list of Tasks into existing data file.
     *
     * @param x PrintWriter object to write the list of Tasks object.
     */
    public void write(PrintWriter x) {
        for (int i = 0; i < this.taskStorage.size(); i++) {
            x.println((i + 1) + ". " + this.taskStorage.get(i));
            i++;
        }
    }

    /**
     * Search for tasks with relevant keyword.
     *
     * @param keyword Keyword used for search.
     * @return An array of indexes of relevant tasks.
     */
    public ArrayList<Integer> search(String keyword) {
        ArrayList<Integer> relevantTaskIndexes = new ArrayList<>();

        for (int i = 0; i < this.taskStorage.size(); i++) {
            if (this.taskStorage.get(i).description.contains(keyword)) {
                relevantTaskIndexes.add(i);
            }
        }
        return relevantTaskIndexes;
    }

    public String getPrintFormat(int index) {
        return this.taskStorage.get(index).toString();
    }
}

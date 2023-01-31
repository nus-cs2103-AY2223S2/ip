package duke.storage;

import duke.tasks.Task;
import duke.exceptions.StorerEmptyException;

import java.util.ArrayList;

/**
 * A List of tasks that are kept track of.
 */
public class TaskList {
    private ArrayList<Task> storer;
    public TaskList(ArrayList<Task> storer) {
        this.storer = storer;
    }
    public TaskList() {
        this.storer = new ArrayList<Task>();
    }
    public void add(Task taskNew) {
        storer.add(taskNew);
    }

    public int size() {
        return this.storer.size();
    }
    /**
     * Obtains and returns a list of tasks in a format to be displayed.
     * @return a list of tasks in the appropriate format.
     * @throws StorerEmptyException
     */
    public String getTaskStrings() throws StorerEmptyException {

        String concat = "";
        for (int i = 1; i <= this.storer.size(); i++) {
            int j = i - 1;
            concat = concat + "\n" + i + ". " + this.storer.get(j);

        }

        if (concat == "") {
            throw new StorerEmptyException();
        }
        return concat;
    }


    public ArrayList<Task> getStorer() {
        return this.storer;
    }

    /**
     * Marks a task as done.
     * @param index index of task in the list by 1-base indexing.
     */
    public void markTask(int index) {
        this.storer.get(index - 1).mark();
    }

    /**
     * Unmarks a task as done.
     * @param index index of task in the list by 1-base indexing.
     */
    public void unmarkTask(int index) {
        this.storer.get(index - 1).unmark();
    }

    /**
     * Obtain a task by its task number.
     * @param index index of task in the list by 1-base indexing.
     * @return the task at the given index.
     */
    public Task get(int index) {
        return this.storer.get(index - 1);
    }

    /**
     * Removes a task given its task number.
     * @param index index of task in the list by 1-base indexing.
     * @return the removed task.
     */
    public Task remove(int index) {
        return this.storer.remove(index - 1);
    }

}

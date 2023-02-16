package duke.storage;

import java.util.ArrayList;

import duke.exceptions.InvalidException;
import duke.exceptions.StorerEmptyException;
import duke.tasks.Task;




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

        String concat = "Here are your current tasks:";
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
    public void markTask(int index) throws InvalidException {
        try {
            this.storer.get(index - 1).mark();
        } catch (Exception e) {
            throw new InvalidException();
        }

    }

    /**
     * Unmarks a task as done.
     * @param index index of task in the list by 1-base indexing.
     */
    public void unmarkTask(int index) throws InvalidException {
        try {
            this.storer.get(index - 1).unmark();
        } catch (Exception e) {
            throw new InvalidException();
        }
    }

    /**
     * Obtain a task by its task number.
     * @param index index of task in the list by 1-base indexing.
     * @return the task at the given index.
     */
    public Task get(int index) throws InvalidException {
        try {
            return this.storer.get(index - 1);
        } catch (Exception e) {
            throw new InvalidException();
        }

    }

    /**
     * Removes a task given its task number.
     * @param index The index of task in the list by 1-base indexing.
     * @return The removed task.
     */
    public Task remove(int index) throws InvalidException {
        try {
            return this.storer.remove(index - 1);
        } catch (Exception e) {
            throw new InvalidException();
        }
    }

    public void replace(TaskList replacement) {
        this.storer = replacement.storer;
    }

}

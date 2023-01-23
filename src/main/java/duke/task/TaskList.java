package duke.task;

import java.util.ArrayList;
import java.util.Collections;

import duke.exception.DukeException;

/**
 * The tasklist represents the list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    private static final long serialVersionUID = 200;
    private TaskList sorted;

    /**
     * Constructs the task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Returns a sorted copy of this tasklist.
     *
     * @return A sorted copy of this tasklist.
     */
    public TaskList sorted() {
        if (sorted != null && sorted.size() == size()) {
            return sorted;
        }

        TaskList temp = new TaskList();
        for (int i = 0; i < size(); i++) {
            temp.add(new Task(Integer.toString(i)));
        }
        Collections.copy(temp, this);
        Collections.sort(temp);
        sorted = temp;
        return temp;
    }

    /**
     * Creates a tasklist containing the tasks that have the string in their names.
     *
     * @param s The string that the task names will contain.
     * @return The tasklist containing the tasks that have the string in their names.
     */
    public TaskList containsStringInName(String s) {
        TaskList containsStringTasks = new TaskList();

        for (int i = 0; i < size(); i++) {
            Task temp = get(i);
            if (temp.hasStringInName(s)) {
                containsStringTasks.add(temp);
            }
        }

        return containsStringTasks;
    }

    /**
     * Marks the task as done using the task number.
     *
     * @param num The task number of the task to be marked.
     * @return The string representation of the task marked as done.
     * @throws DukeException If the num is invalid or there are no tasks.
     */
    public String mark(int num) throws DukeException {
        if (size() == 0) {
            throw new DukeException("There are no tasks to be marked.");
        }

        try {
            get(num).setAsDone();
            return get(num).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number between 1 and " + size());
        }
    }

    /**
     * Unmarks the task as done using the task number.
     *
     * @param num The task number of the task to be unmarked.
     * @return The string representation of the task unmarked.
     * @throws DukeException If the num is invalid or there are no tasks.
     */
    public String unmark(int num) throws DukeException {
        if (size() == 0) {
            throw new DukeException("There are no tasks to be unmarked.");
        }

        try {
            get(num).setAsNotDone();
            return get(num).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number between 1 and " + size());
        }
    }

    /**
     * Deletes the task using the task number.
     *
     * @param num The task number of the task to be deleted.
     * @return The string representation of the task unmarked.
     * @throws DukeException If the num is invalid or there are no tasks.
     */
    public String delete(int num) throws DukeException {
        if (size() == 0) {
            throw new DukeException("There are no tasks to be deleted.");
        }

        try {
            String s = get(num).toString();
            remove(num);
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number between 1 and " + size());
        }
    }
}

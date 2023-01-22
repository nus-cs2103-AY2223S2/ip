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
     * Marks the task as done using the task number.
     *
     * @param num
     * @return The string representation of the task marked as done.
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
     * @param num
     * @return The string representation of the task unmarked.
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
     * @param num
     * @return The string representation of the task unmarked.
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

package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * The History class represents the past states of the tasklist.
 */
public class History {
    private static final int MAX_HISTORY = 30;
    private static History history = new History();
    private ArrayList<TaskList> previousTaskList;
    private int current;

    private History() {
        previousTaskList = new ArrayList<>(30);
        current = 0;
    }

    /**
     * Retrives the single instance of history.
     *
     * @return The instance of history.
     */
    public static History getInstance() {
        return history;
    }

    /**
     * Adds the current state of the tasklist to history.
     *
     * @param state The state of the tasklist to add.
     */
    public void addState(TaskList state) {
        if (current == MAX_HISTORY) {
            previousTaskList.remove(0);
            current -= 1;
        }
        previousTaskList.add(new TaskList(state));
        current += 1;
    }

    /**
     * Retrieves the direct previous state of the Tasklist.
     *
     * @return The previous state of the tasklist.
     * @throws DukeException if there is nothing to undo.
     */
    public TaskList undoState() throws DukeException {
        if (current <= 0) {
            throw new DukeException("There is nothing to undo!");
        }
        current -= 1;
        return previousTaskList.get(current);
    }
}

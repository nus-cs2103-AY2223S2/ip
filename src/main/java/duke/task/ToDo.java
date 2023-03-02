package duke.task;

import java.util.regex.PatternSyntaxException;

/**
 * Represents a ToDo task
 */
public class ToDo extends Task {
    /**
     * Returns a ToDo stored as not done
     *
     * @param task String of ToDo to be stored
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Returns a ToDo with task and isDone stored
     *
     * @param task String of ToDo to be stored
     * @param isDone boolean of if the ToDo is done
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }
    /**
     * Return the String of the ToDo formatted to be displayed
     *
     * @return String formatted String
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Return the String of the ToDo used to be saved
     *
     * @return String formatted String
     */
    @Override
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("T | %d | %s", done, getTask());
    }
}

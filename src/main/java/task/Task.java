package task;

import java.io.File;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the common features of a Task object.
 *
 * @author Bryan Ong
 */
public abstract class Task {

    private final String NAME;
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private boolean isDone;
    private static int count = 0;

    public Task(String name, boolean isDone) {
        this.NAME = name;
        this.isDone = isDone;
        count++;
    }

    /**
     * This method checks if keyword is present in current task.
     *
     * @param keyword to be crosschecked.
     * @return if present or not.
     */
    public boolean hasKeyword(String keyword) {
        String[] keys = NAME.split(" ");
        if (keys.length == 1) {
            return keys[0].equalsIgnoreCase(keyword);
        } else {
            for (String key : keys) {
                if (key.equalsIgnoreCase(keyword)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method handles the marking of task.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * This method handles the unmarking of task.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * This method returns the number of Task.
     *
     * @return int Number of task.
     */
    public int numberTask() {
        return count;
    }

    /**
     * This method decreases the Task count.
     */
    public void minus() {
        count--;
    }

    public void plus() {
        count++;
    }
    /**
     * Abstract method for writting of Task into file.
     *
     * @param file Data file to be written into.
     * @return String to be written.
     */
    public abstract String write(File file);

    /**
     * This method handles the creation of String
     * of status and name of Task for print.
     *
     * @return String of common features.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + NAME
                    : "[ ] " + NAME;
    }

    /**
     * This method handles the creation of String
     * of status and name of Task for write.
     *
     * @return String of common features.
     */
    public String toWrite() {
        return isDone ? "1 | " + NAME
                : "0 | " + NAME;
    }
}

package task;

import java.io.File;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the common features of a Task object.
 * @author Bryan Ong
 */
public abstract class Task {

    private final String NAME;
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private boolean done;
    private static int count = 0;

    public Task(String name, boolean done) {
        this.NAME = name;
        this.done = done;
        count++;
    }


    /**
     * This method handles the marking of task.
     */
    public void mark() {
        done = true;
    }

    /**
     * This method handles the unmarking of task.
     */
    public void unmark() {
        done = false;
    }

    /**
     * This method returns the number of Task.
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

    /**
     * Abstract method for writting of Task into file.
     * @param file Data file to be written into.
     * @return String to be written.
     */
    public abstract String write(File file);

    /**
     * This method handles the creation of String
     * of status and name of Task for print.
     * @return String of common features.
     */
    @Override
    public String toString() {
        return done ? "[X] " + NAME
                    : "[ ] " + NAME;
    }

    /**
     * This method handles the creation of String
     * of status and name of Task for write.
     * @return String of common features.
     */
    public String toWrite() {
        return done ? "1 | " + NAME
                : "0 | " + NAME;
    }
}

package task;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * General Task abstract class
 * @see Serializable
 */
public abstract class Task implements Serializable {

    protected final String desc;
    protected final boolean done;
    
    /**
     * @param desc Task description
     * @param done Task completed info
     */
    protected Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    @Override
    public String toString() {
        char mark = ' ';
        if (done) {
            mark = 'X';
        }

        return String.format("[%c] %s", mark, this.desc);
    }

    /**
     * @return true if task has date, false otherwise.
     * @see LocalDate
     */
    public abstract boolean hasDate(LocalDate date);

    /**
     * @return new task that is marked as done.
     */
    public abstract Task markDone();

    /**
     * @return new task that is marked as not done.
     */
    public abstract Task markNotDone();
}

package alfred.task;

import java.time.LocalDate;


// Can consider this class to be an abstract class?

/**
 * Represents a task given by the user.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task object that represents a unique task given by the user.
     * @param description Provides the name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    // I remember there's a modifier only allowing classes in same file to access?

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Un-mark the task as completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Converts the file into a format that is suitable for data storage.
     * @return The converted format of the task that describes its attributes.
     */
    public String addToFile() {
        String str = String.format(" | %d | %s",
                isDone ? 1 : 0, this.description);
        return str + "\n";
    }

    /**
     * Checks if the task contains a LocalDate object.
     * @param date The LocalDate object that represents the date of the task.
     * @return True if the task contains the given date, else False.
     */
    public boolean containsDate(LocalDate date) {
        return false;
    }

    /**
     * Converts the task into a String which represents a readable format for the user.
     * @return The readable format of the task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}

package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a Todo task
 */
public class Todo extends Task {
    /**
     * Initializes a Todo object.
     * @param taskText the task description
     */
    public Todo(String taskText) {
        super(taskText);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the formatted text to be stored.
     * in the Duke text file.
     *
     * @return the formatted string
     */
    public String writeFile() {
        return String.format("T|%s|%s", this.getCurrentStatus(), this.getTaskText());
    }

    /**
     * Returns the date and time for Todo type
     * which is null
     *
     * @return null
     */
    public LocalDateTime getDate() {
        return null;
    }

    /**
     * Returns the letter representation for Todo type
     * to store in the Duke text file
     *
     * @return the string letter
     */
    public String getTaskType() {
        return "T";
    }
}

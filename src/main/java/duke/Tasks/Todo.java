package duke.tasks;

/**
 * Represents a to-do task
 */
public class Todo extends Task {

    /**
     * Represents to-do constructor
     * @param description task name
     * @param isDone check whether marks as done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);

    }

    /**
     * Overrides toString method
     * @return formatted message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Formats a to-do task into a suitable String
     * @return formatted message
     */
    @Override
    public String file() {
        return "T | " + super.file();
    }
}

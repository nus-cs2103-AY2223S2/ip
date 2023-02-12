package clippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * An abstraction over all tasks tracked by this program.
 *
 * @author chunzkok
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private PriorityLevel priority;

    /**
     * Helps to create a subclass of Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        // assumed to be not complete upon initialisation
        // would not make sense to add a finished task to the list
        this.isDone = false;
        this.priority = PriorityLevel.NONE;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void uncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("%s %s (Priority: %s)",
                this.isDone ? "[X]" : "[ ]",
                this.description,
                this.priority.name());
    }

    /**
     * Returns a string representation of the Task in CSV form.
     * @return A string representation of the Task in CSV form.
     */
    public String getCsvString() {
        return String.format("%s,%b,%s", this.description, this.isDone, this.priority.name());
    }

    /**
     * Identifies the appropriate subclass of Task to be instantiated based on a CSV line.
     *
     * @param csvString A line of CSV values.
     * @return The corresponding Task represented by the CSV line.
     */
    public static Task parseCsvString(String csvString) {
        System.out.println(csvString);
        String[] arguments = csvString.split(",");
        Task result = null;
        try {
            switch (arguments[0]) {
            case "D":
                result = new Deadline(arguments[1], LocalDate.parse(arguments[4]));
                break;
            case "T":
                result = new ToDo(arguments[1]);
                break;
            case "E":
                result = new Event(
                        arguments[1],
                        LocalDate.parse(arguments[4]),
                        LocalDate.parse(arguments[5]));
                break;
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println(">>> Unable to parse date: " + e.toString());
            return null;
        }
        result.isDone = Boolean.parseBoolean(arguments[2]);
        result.setPriority(PriorityLevel.valueOf(arguments[3]));
        return result;
    }

    /**
     * Returns true if keyword is found in Task's description.
     * @param keyword The keyword to search for in the Task's description.
     * @return True if keyword is found in Task's description.
     */
    public boolean hasKeywordInDescription(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Sets the priority level of the current task.
     *
     * @param priority The priority level of the task.
     */
    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }
}

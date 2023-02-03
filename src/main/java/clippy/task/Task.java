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
    private boolean done;

    /**
     * Helps to create a subclass of Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        // assumed to be not complete upon initialisation
        // would not make sense to add a finished task to the list
        this.done = false;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.done = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void uncomplete() {
        this.done = false;
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return (this.done ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Returns a string representation of the Task in CSV form.
     * @return A string representation of the Task in CSV form.
     */
    public String getCsvString() {
        return String.format("%s,%b", this.description, this.done);
    }

    /**
     * Identifies the appropriate subclass of Task to be instantiated based on a CSV line.
     *
     * @param csvString A line of CSV values.
     * @return The corresponding Task represented by the CSV line.
     */
    public static Task parseCsvString(String csvString) {
        String[] arguments = csvString.split(",");
        Task result = null;
        try {
            switch (arguments[0]) {
            case "D":
                result = new Deadline(arguments[1], LocalDate.parse(arguments[3]));
                break;
            case "T":
                result = new ToDo(arguments[1]);
                break;
            case "E":
                result = new Event(
                        arguments[1],
                        LocalDate.parse(arguments[3]),
                        LocalDate.parse(arguments[4]));
                break;
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println(">>> Unable to parse date: " + e.toString());
            return null;
        }
        result.done = Boolean.parseBoolean(arguments[2]);
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
}

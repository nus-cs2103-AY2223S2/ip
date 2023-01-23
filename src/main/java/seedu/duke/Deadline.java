package seedu.duke;

import java.time.LocalDate;

/**
 * Class for tasks that have a deadline.
 * it extends the Task class.
 *
 * @param type task type.
 * @param date the deadline date.
 */
public class Deadline extends Task{
    protected String type = "[ D ]";
    protected LocalDate date;
    /**
     * Constructs a Deadline task object and initializes the needed parameters.
     *
     * @param description description of the task.
     * @param a the deadline date.
     */
    public Deadline(String description, String a) {
        super(description);
        System.out.println(date);
        this.date = LocalDate.parse(a);
    }

    /**
     * Returns the string describing the task.
     *
     * @return description of task.
     */
    @Override
    public String toString() {
        return type + super.toString() + " by: " + date + " ";
    }
}

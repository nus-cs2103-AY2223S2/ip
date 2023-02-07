package duke.task;

/**
 * This class is a task object, when it breaks down the deadline into the respective alphanumeric form.
 */
public class Deadline extends Task {

    protected String targetDate;
    protected String targetTiming;

    /**
     * Constructor for the deadline class.
     *
     * @param description task description in string.
     * @param date deadline of the task to be completed.
     */
    public Deadline(String description, String date, String timing) {
        super(description);
        this.targetDate = date;
        this.targetTiming = timing;
    }

    /**
     * Method that returns a string of the formmated deadline task.
     *
     * @return String Output of the date line object formatted.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.targetDate + " " + targetTiming + ")";
    }
}

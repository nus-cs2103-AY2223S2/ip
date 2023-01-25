import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    /** 
     * A public constructor to initialize Deadline instance.
     * 
     * @param task Task name.
     * @param deadline Task deadline.
     */
    Deadline(String task, String deadline) {
        super(task);
        this.deadline = LocalDate.parse(deadline);
    }

    /** 
     * Returns the description of Deadline.
     * 
     * @return Deadline description.
     */
    @Override
    public String getDescription() {
        return "deadline " + super.getTaskName() + "/by " + this.deadline;
    }

    /** 
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
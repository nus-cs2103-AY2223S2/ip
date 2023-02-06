package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Class for Deadline object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String taskType = "D";


    /**
     * Constructor for a Deadline object.
     *
     * @param description
     * @param by
     */

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public void editInfo(String partToBeEdited, String content) {
        switch (partToBeEdited) {
            case "desc":
                this.description = content;
                break;
            case "by":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime dueDate = LocalDateTime.parse(content, formatter);
                this.by = dueDate;
                break;
            default:
                break;
        }
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    public LocalDateTime getDeadline() {
        return this.by;
    }
    /**
     * Message printed when a new Deadline task is added.
     *
     * @return String representing the Deadline task information.
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a")) + ")";
    }
}

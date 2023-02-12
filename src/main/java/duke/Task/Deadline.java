package duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline task which is to be stored by Duke. A Deadline has a task name, as well as a due time.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * The constructor for a deadline object.
     * @param name The name of the task.
     * @param deadline The time at which the task is due.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String taskToData() {
        return String.format("[D] | %s | %s | %s", this.getStatusIcon(), this.name, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", name, deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}

package duke.task;

import java.time.LocalDate;

/**
 * A task implementation with a due date.
 */
public class Deadline extends Task {

    /**
     * The due date of the task.
     */
    private final LocalDate by;

    /**
     * Constructs a new Deadline with the given description and due date.
     * @param desc the description of the task
     * @param by the due date of the task
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by.format(format) + ")";
  }
}

package duke.task;

import java.time.LocalDateTime;

/**
 * A task with a deadline (date and time).
 */
public class Deadline extends Task {

    protected String ddmmyyyy;
    protected String hhmm;
    protected LocalDateTime datetime;

    /**
     * Creates a new Deadline object.
     * @param desc A description of the task.
     * @param ddmmyyyy The date the task is due.
     * @param hhmm The time the task is due.
     */
    public Deadline(String desc, String ddmmyyyy, String hhmm) {
        super(desc);
        this.ddmmyyyy = ddmmyyyy;
        this.hhmm = hhmm;
        String[] date = ddmmyyyy.split("/");
        this.datetime = LocalDateTime.of(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]), Integer.parseInt(date[0]),
                Integer.parseInt(hhmm.substring(0, 2)), Integer.parseInt(hhmm.substring(2)));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s %s)",
                dateFormatter.format(datetime), timeFormatter.format(datetime));
    }

    /**
     * Gets the deadline details to save in data/tasks.txt.
     * @return Deadline details.
     */
    public String getDetailsToSave() {
        return String.format("deadline %s %s %s\n%s", isDone, ddmmyyyy, hhmm, desc);
    }

}

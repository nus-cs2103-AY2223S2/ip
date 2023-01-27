package duke.tasks;

import java.time.LocalDate;

/**
 * Stores inputs of deadline tasks
 */
public class Deadline extends Task {
    private final LocalDate date;
    private final String time;
    private final String[] period;

    /**
     * Acts as constructor for deadline class
     *
     * @param name  sets name of the tasks
     * @param frame sets deadline date
     */
    public Deadline(String name, String frame) {
        super(name);
        this.period = frame.split(" ");
        if (period[0].contains("/")) {
            this.date = LocalDate.parse(period[0].replaceAll("/", "-"));
        } else {
            this.date = LocalDate.parse(period[0]);
        }
        this.time = period[1];
    }

    /**
     * Displays name, date and time of the deadline task
     *
     * @return shows the deadline item
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.getDayOfMonth() + " "
                + date.getMonth() + " " + date.getYear() + ", " + time + " )";
    }
}

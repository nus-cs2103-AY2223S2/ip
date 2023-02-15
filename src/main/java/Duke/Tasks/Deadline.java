package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline class
 */
public class Deadline extends Task{
    protected LocalDateTime time;

    /**
     * Constructor of Deadline
     * @param desc the description
     * @param time the time
     * @param done whether the task is done
     */
    public Deadline(String desc, LocalDateTime time, boolean done) {
        super(done, desc);
        this.time = time;
    }

    /**
     * Marks the Deadline as done
     * @return Deadline
     */
    @Override
    public Deadline mark() {
        super.mark();
        return this;
    }

    /**
     * Unmarks the deadline
     * @return Deadline
     */
    @Override
    public Deadline unmark() {
        super.unmark();
        return this;
    }

    /**
     * Run the deadline and add to table
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        table.add(this);
        monitor.displayAdd(table, (table.size() - 1));
        disk.write(table.getTable());
        String message = "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + this.toString() + "\n" +
                "     Now you have " + table.size() +  " task(s) in the list.\n" +
                "    ____________________________________________________________\n";
        return message;
    }

    /**
     * Override the String method and convert the deadline to String
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Override the reformat method and convert to standard output
     * @return String
     */
    @Override
    public String reformat() {
        String d;
        if (isDone) {
            d = "1";
        } else {
            d = "0";
        }
        return "D | " + d + " | " + this.desc + " | " + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

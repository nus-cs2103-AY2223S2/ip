package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;

/**
 * Represents the Deadline class
 */
public class Deadline extends Task{
    protected String time;

    /**
     * Constructor of Deadline
     * @param desc the description
     * @param time the time
     * @param done whether the task is done
     */
    public Deadline(String desc, String time, boolean done) {
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
    public void run(TaskTable table, Monitor monitor) {
        table.add(this);
        monitor.displayAdd(table, (table.size() - 1));
    }

    /**
     * Override the String method and convert the deadline to String
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}

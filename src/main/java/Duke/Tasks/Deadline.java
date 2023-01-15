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
     * @param desc
     * @param time
     * @param done
     */
    public Deadline(String desc, String time, boolean done) {
        super(done, desc);
        this.time = time;
    }

    @Override
    public Deadline mark() {
        super.mark();
        return this;
    }

    @Override
    public Deadline unmark() {
        super.unmark();
        return this;
    }

    @Override
    public void run(TaskTable table, Monitor monitor) {
        table.add(this);
        monitor.displayAdd(table, (table.size() - 1));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}

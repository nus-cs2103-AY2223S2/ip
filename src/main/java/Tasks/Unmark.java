package Tasks;

/**
 * Represents the class Unmark
 */
public class Unmark extends Task {

    int index;

    /**
     * The constructor of Unmark
     * @param index the index of the task
     */
    public Unmark(int index) {
        super(false, "Undone");
        this.index = index;
    }

    /**
     * Override the run method and run the Unmark
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public void run(TaskTable table, Monitor monitor) {
        table.unmark(index);
        monitor.displayUnmark(table, index);
        super.run(table, monitor);
    }
}

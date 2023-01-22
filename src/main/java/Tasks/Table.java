package Tasks;

/**
 * Represents task table class
 */
public class Table extends Task {

    /**
     * The constructor of task table
     */
    public Table() {
        super(false, "list");
    }

    /**
     * Override the run method and show the table
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public void run(TaskTable table, Monitor monitor, Disk disk) {
        monitor.showTable(table);
    }
}

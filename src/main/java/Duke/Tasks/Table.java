package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;

/**
 * Represents task table class
 */
public class Table extends Task {

    /**
     * The constructor of taks table
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
    public void run(TaskTable table, Monitor monitor) {
        monitor.showTable(table);
    }
}

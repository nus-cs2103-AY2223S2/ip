package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;

/**
 * Represents List task class
 */
public class Table extends Task {


    public Table() {
        super(false, "list");
    }

    @Override
    public void run(TaskTable table, Monitor monitor) {
        monitor.showTable(table);
    }
}

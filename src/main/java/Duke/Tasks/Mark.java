package Duke.Tasks;

import Duke.Monitor;
import Duke.TaskTable;

/**
 * Represents the Mark class
 */
public class Mark extends Task {
    int index;

    /**
     * The constructor for Mark class
     * @param index the index of the task
     */
    public Mark(int index) {
        super(true, "Done");
        this.index = index;
    }

    /**
     * Override the run method and execute the marking process
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public void run(TaskTable table, Monitor monitor) {
        table.mark(index);
        monitor.displayMark(table, index);
        super.run(table, monitor);
    }
}

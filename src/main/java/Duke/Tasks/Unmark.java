package Duke.Tasks;

import Duke.Monitor;
import Duke.TaskTable;

public class Unmark extends Task {

    int index;

    public Unmark(int index) {
        super(false, "Undone");
        this.index = index;
    }

    @Override
    public void run(TaskTable table, Monitor monitor) {
        table.unmark(index);
        monitor.displayUnmark(table, index);
        super.run(table, monitor);
    }
}

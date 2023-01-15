package Duke.Tasks;

import Duke.Monitor;
import Duke.TaskTable;

public class Mark extends Task {
    int index;

    public Mark(int index) {
        super(true, "Done");
        this.index = index;
    }

    @Override
    public void run(TaskTable table, Monitor monitor) {
        table.mark(index);
        monitor.displayMark(table, index);
        super.run(table, monitor);
    }
}

package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;

public class Delete extends Task{

    int i;

    public Delete(int i) {
        super(false, "delete");
        this.i = i;
    }

    @Override
    public void run(TaskTable table, Monitor monitor) {
        Task removedJob = table.delete(i);
        monitor.displayDelete(table, removedJob);

    }
}

package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;

public class Todo extends Task {

    public Todo(String desc, boolean done) {
        super(done, desc);
    }

    @Override
    public Todo mark() {
        super.mark();
        return this;
    }

    @Override
    public Todo unmark() {
        super.unmark();
        return this;
    }

    @Override
    public void run(TaskTable table, Monitor monitor)  {
        table.add(this);
        monitor.displayAdd(table, table.size() - 1);
    }

    @Override
    public String toString() {
        return "[T]"  + super.toString();
    }




}

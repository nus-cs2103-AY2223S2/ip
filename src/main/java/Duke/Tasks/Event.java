package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;


public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String desc, String startTime, String endTime, boolean done) {
        super(done, desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Event mark() {
        super.mark();
        return this;
    }

    @Override
    public Event unmark() {
        super.unmark();
        return this;
    }

    @Override
    public void run(TaskTable table, Monitor monitor) {
        table.add(this);
        monitor.displayAdd(table, table.size()-1);

    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to " + this.endTime + ")";
    }





}

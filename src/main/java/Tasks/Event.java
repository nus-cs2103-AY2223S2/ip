package Tasks;


/**
 * Represents the Event class
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;
    protected String time;

    /**
     * The constructor of Event
     * @param desc the description of event
     * @param time the time of event
     * @param done whether the event is done
     */
    public Event(String desc, String time, boolean done) {
        super(done, desc);
        this.time = time;
    }

    /**
     * The method marks the event as done
     * @return Event
     */
    @Override
    public Event mark() {
        super.mark();
        return this;
    }

    /**
     * The method unmarks the event
     * @return Event
     */
    @Override
    public Event unmark() {
        super.unmark();
        return this;
    }

    /**
     * The method adds the event to the table
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public void run(TaskTable table, Monitor monitor, Disk disk) {
        table.add(this);
        monitor.displayAdd(table, table.size()-1);
        disk.write(table.getTable());

    }

    /**
     * Override the String method and convert the event to String
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: /from" + this.time + ")";
    }

    @Override
    public String reformat() {
        String d;
        if (done) {
            d = "1";
        } else {
            d = "0";
        }
        return "E | " + d + " | " + this.desc +" | " + this.time;
    }
}

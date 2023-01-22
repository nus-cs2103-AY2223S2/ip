package Tasks;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event class
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * The constructor of Event
     * @param desc the description of event
     * @param startTime the start time of event
     * @param endTime the end time of event
     * @param done whether the event is done
     */
    public Event(String desc, LocalDateTime startTime, LocalDateTime endTime, boolean done) {
        super(done, desc);
        this.startTime = startTime;
        this.endTime = endTime;
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
        return "[E]" + super.toString() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to " + this.endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String reformat() {
        String d;
        if (done) {
            d = "1";
        } else {
            d = "0";
        }
        return "E | " + d + " | " + this.desc +" | " + this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                " | " + this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }



}

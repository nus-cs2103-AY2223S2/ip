package duke.tasks;

import java.time.LocalDate;

/**
 * A task with a specified starting and ending times.
 */
public class TaskEvent extends Task {
    /** The starting time for this event task. */
    public final LocalDate fromTime;
    /** The ending time for this event task. */
    public final LocalDate toTime;

    public TaskEvent(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = LocalDate.parse(fromTime);
        this.toTime = LocalDate.parse(toTime);
    }

    /**
     * Parses a event task that has been encoded into a string, into a 
     * 'TaskEvent' instance.
     * 
     * @param input The encoded event task.
     * @return The event task that was encoded.
     * @throws DukeSaveLoadException If there's a problem in parsing the encoded task.
     */
    public static TaskEvent loadFromString(String input) {
        String[] values = Task.decodeValues(input);
        boolean isDone = values[1].equals("1");
        String description = values[2];
        String fromTime = values[3];
        String toTime = values[4];
        
        TaskEvent output = new TaskEvent(description, fromTime, toTime);
        if (isDone) {
            output.markAsDone();
        }
        return output;
    }

    @Override
    public String encodeAsString() {
        return Task.encodeValues(new String[]{ 
                "E", 
                this.isDone ? "1" : "0", 
                this.description, 
                this.fromTime.toString(), 
                this.toTime.toString() });
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)", 
                super.toString(), 
                Task.formatDate(this.fromTime), 
                Task.formatDate(this.toTime));
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof TaskEvent)) {
            return false;
        }
        TaskEvent other = (TaskEvent) obj;
        if (!other.fromTime.equals(this.fromTime)) {
            return false;
        }
        if (!other.toTime.equals(this.toTime)) {
            return false;
        }
        return true;
    }
}

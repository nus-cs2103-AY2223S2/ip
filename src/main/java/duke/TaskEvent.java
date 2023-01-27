package duke;

import java.time.LocalDate;

public class TaskEvent extends Task {
    public final LocalDate fromTime;
    public final LocalDate toTime;

    public TaskEvent(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = LocalDate.parse(fromTime);
        this.toTime = LocalDate.parse(toTime);
    }

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
            this.toTime.toString()
        });
    }

    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)", 
            super.toString(), 
            Task.formatDate(this.fromTime), 
            Task.formatDate(this.toTime)
        );
    }
}

package duke.tasks;

import java.time.LocalDate;

public class TaskDeadline extends Task {
    public final LocalDate endTime;

    public TaskDeadline(String description, String endTime) {
        super(description);
        this.endTime = LocalDate.parse(endTime);
    }

    public static TaskDeadline loadFromString(String input) {
        String[] values = Task.decodeValues(input);
        boolean isDone = values[1].equals("1");
        String description = values[2];
        String endTime = values[3];
        
        TaskDeadline output = new TaskDeadline(description, endTime);
        if (isDone) {
            output.markAsDone();
        }
        return output;
    }

    @Override
    public String encodeAsString() {
        return Task.encodeValues(new String[]{ 
            "D", 
            this.isDone ? "1" : "0", 
            this.description.toString(), 
            this.endTime.toString()
        });
    }

    @Override
    public String toString() {
        return String.format(
            "[D]%s (by: %s)", 
            super.toString(), 
            Task.formatDate(this.endTime)
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof TaskDeadline)) {
            return false;
        }
        TaskDeadline other = (TaskDeadline) obj;
        if (!other.endTime.equals(other.endTime)) {
            return false;
        }
        return true;
    }
}

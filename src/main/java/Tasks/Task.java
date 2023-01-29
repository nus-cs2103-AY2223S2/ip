package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    private final String taskName;
    private boolean completionStatus;


    public Task(String taskName) {
        this(taskName, false);
    }

    public Task(String taskName, boolean state) {
        this.completionStatus = state;
        this.taskName = taskName;
    }

    public void toggleState() {
        completionStatus = !completionStatus;
    }

    public boolean getCompletionStatus() {
        return completionStatus;
    }

    @Override
    public String toString() {
        return "[" + (completionStatus ? "X" : " ") + "] " + taskName;
    }

    protected String displayLocalDate(LocalDateTime input) {
        return input.format(DateTimeFormatter.ofPattern("E h:ma', 'MMM d yyyy"));
    }

}

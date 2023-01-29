package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected final String taskName;
    private boolean completionStatus;


    public Task(String taskName) {
        this.completionStatus = false;
        this.taskName = taskName;
    }

    public void toggleState() {
        completionStatus = !completionStatus;
    }

    public boolean getCompletionStatus() {
        return completionStatus;
    }

    public void loadCompletionStatus(String inp) {
        completionStatus = (inp == "1");
    }

    public abstract String toSaveData();


    @Override
    public String toString() {
        return "[" + (completionStatus ? "X" : " ") + "] " + taskName;
    }

    protected String displayLocalDate(LocalDateTime input) {
        return input.format(DateTimeFormatter.ofPattern("E h:mma', 'MMM d yyyy"));
    }

}

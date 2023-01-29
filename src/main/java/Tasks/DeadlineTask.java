package Tasks;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    LocalDateTime dueDate;

    public DeadlineTask(String taskName, LocalDateTime dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + displayLocalDate(dueDate) + ")";
    }

}

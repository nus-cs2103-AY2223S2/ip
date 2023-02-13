import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private String deadlineTime;
    private Date dueDate;

    Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    Deadline(String description, Date dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getOutputFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadlineTime);
    }

    @Override
    public String toString() {
        if (dueDate != null) {
            DateFormat dateDueFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("[D]%s (by: %s)", super.toString(), dateDueFormat.format(dueDate));
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), deadlineTime);
        }
    }
}
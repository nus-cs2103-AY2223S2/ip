package jane.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Makes a deadline task
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    /**
     * @param num is Deadline ID
     * @param description is deadline description (what to do)
     * @param deadline is the date that it is due
     */
    public Deadline(int num, String description, LocalDateTime deadline) {
        super(num, description);
        this.deadline = deadline;
    }
    @Override
    public String save() {
        int i = 0;
        if (this.isDone) {
            i = 1;
        }
        return String.format("D|%d| %s |%s", i, this.description, this.deadline);
    }
    @Override
    public String toString() {
        String parsed = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("%d. [D][%s] %s(%s)", this.num, this.getStatusIcon(), this.description, parsed);
    }

}


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadlineDay;

    Deadline(String desc, boolean done, LocalDateTime deadlineDay) {
        super(desc, done);
        this.deadlineDay = deadlineDay;
    }

    String getDeadlineDay(){
        return this.deadlineDay.toString();
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + formatDate(this.deadlineDay) + ")";
    }
}

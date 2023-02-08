import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
<<<<<<< HEAD
    LocalDateTime deadlineDay;
=======
    private String deadlineDay;
>>>>>>> branch-Level-8

    Deadline(String desc, boolean done, LocalDateTime deadlineDay) {
        super(desc, done);
        this.deadlineDay = deadlineDay;
    }

    String getDeadlineDay(){
        return this.deadlineDay;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + formatDate(this.deadlineDay) + ")";
    }
}

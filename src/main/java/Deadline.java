import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate endTime;

    public Deadline(String s, String endTime) {
        super(s);
        this.endTime = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Deadline(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        this.endTime = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String writeToFile() {
        if (!taskDone) {
            return "D| |"
                    + this.taskName
                    + "|"
                    + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "D|X|"
                + this.taskName
                + "|"
                + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[D][ ] " + this.taskName
                    + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
        }
        return "[D][X] " + this.taskName
                + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }

}

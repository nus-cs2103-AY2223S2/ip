import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean done;
    // private String type;

    private String description;
    private LocalDate deadline;

    public Task(String description) {
        this.description = description;
        this.done = false;
        this.deadline = null;
    }

    public Task(String description,LocalDate deadline) {
        this.description = description;
        this.done = false;
        this.deadline = deadline;
    }

    public String mark() {
        this.done = true;
        return this.toString();
    }

    public String unMark() {
        this.done = false;
        return this.toString();
    }

    @Override
    public String toString() {
        String doneString;
        if (done) {
            doneString = "[X]";
        } else {
            doneString = "[ ]";
        }
        String dateString;
        if (this.deadline == null) {
            dateString = "";
        } else {

            dateString = " by " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        return doneString + " " + description + dateString;
    }
}

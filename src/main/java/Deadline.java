import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String time = "";
    private LocalDate timeParsed;

    public Deadline(int id, String task, String time) {
        super(id, task);
        this.time = time;
    }

    public Deadline(int id, String task, LocalDate timeParsed) {
        super(id, task);
        this.timeParsed = timeParsed;
    }

    @Override
    public String printTask() {
        return this.isDone()
                ? "[D][x] " + this.getTask()
                        + " (Due: "
                        + this.timeParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                : "[D][ ] " + this.getTask()
                        + " (Due: "
                        + this.timeParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

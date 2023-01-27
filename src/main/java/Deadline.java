import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate timeParsed;

    public Deadline(int id, String task, LocalDate timeParsed) {
        super(id, task);
        this.timeParsed = timeParsed;
    }

    @Override
    public String printTask() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return this.isDone()
                ? "[D][x] " + this.getTask()
                        + " (Due: "
                        + this.timeParsed.format(formatter) + ")"
                : "[D][ ] " + this.getTask()
                        + " (Due: "
                        + this.timeParsed.format(formatter) + ")";
    }
}

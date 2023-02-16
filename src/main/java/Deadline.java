import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private LocalDate deadline;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM uuuu");

    public Deadline(String taskName, String deadline){
        super(taskName);
        String[] fromDate = deadline.split(" ", 2);
        LocalDate ld = LocalDate.parse(fromDate[1].replace(" ", ""));
        this.deadline = ld;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by " + deadline.format(FORMATTER) + ")";
    }
}

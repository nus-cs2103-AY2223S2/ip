import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private String by;
    private LocalDate byDate;

    public DeadlineTask(String title, String by) {
        super(title);
        this.by = formatIfDate(by, byDate);
    }

    private String formatIfDate(String input, LocalDate date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd LLL uuuu");
        try {
            LocalDate output = LocalDate.parse(input, inputFormat);
            date =  output;
            return output.format(outputFormat);
        } catch (DateTimeParseException e) {
            return input;
        }
    }

    public String save() {
        String status = this.isDone ? "DONE/+/" : "NOTDONE/+/";
        return "DEADLINE/+/" + status + this.title + "/+/" + this.by + "\n";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[D]" + status + this.title + " (by: " + this.by + ")";
    }
}

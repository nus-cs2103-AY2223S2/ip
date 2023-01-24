import java.time.LocalDate;
import java.util.regex.Pattern;

public class Deadline extends Task {
    public static final Pattern p = Pattern.compile("(.+) /by (\\d{4}-\\d{2}-\\d{2})");
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        classIcon = "D";
    }

    @Override
    public String toString() {
        return String.format("%s By: %s", super.toString(), by.format(Task.outputFormat));
    }

    @Override
    public String toLog() {
        return String.format("%s /by %s", super.toString(), by.format(Task.inputFormat));
    }

}
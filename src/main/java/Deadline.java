import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate byWhen;
    public Deadline(String desc,LocalDate byWhen) {
        super(desc,"D");
        this.byWhen = byWhen;
    }

    public LocalDate getByWhen() {
        return byWhen;
    }

    public String toString() {
        return super.toString() + String.format("[by %s]", this.byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate dl;

    Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.dl = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" +
                super.toString() +
                " (by: " + dl.getMonth() + ' ' +
                + dl.getDayOfMonth() + ' ' +
                dl.getYear() + ", " +
                dl.getDayOfWeek() + ")";
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// deadline is a type of task that takes in an additional string
// as its deadline
public class Deadline extends Task {

    private String timingString;
    private LocalDate timingLocalDate;

    public Deadline(String description, String deadlineTiming) {
        super(description);
        try {
            timingLocalDate = LocalDate.parse(deadlineTiming);
            timingString = timingLocalDate.format(
                    DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            timingString = deadlineTiming;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timingString + ")";
    }
}
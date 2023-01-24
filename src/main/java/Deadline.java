import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " "
                + super.getDescription() + "(by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public static Deadline addDeadline(String details) {
        String description = details.substring(0, details.indexOf("/by"));
        String byString = details.substring(details.indexOf("/by") + "/by".length() + 1);

        String[] possibleFormats = {"yyyy-MM-dd", "dd/MM/yyyy HHmm", "d/MM/yyyy HHmm", "MMM dd yyyy", "MMMM dd yyyy"};
        LocalDateTime by = null;
        for (int i = 0; i < possibleFormats.length; i++) {
            try {
                by = LocalDateTime.parse(byString, DateTimeFormatter.ofPattern(possibleFormats[i]));
                break;
            } catch (DateTimeParseException ignored) {
            }
        }

        Deadline t = new Deadline(description, by);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        return t;
    }
}
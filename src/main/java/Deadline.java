import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Quest {
    private LocalDateTime by;
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm dd/MM/yy");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " by: " + by.format(outputFormatter);
    }
}

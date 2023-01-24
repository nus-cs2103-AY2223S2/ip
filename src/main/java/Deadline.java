import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime endDate;
    public Deadline(String description, String endDate, boolean fromFile) {
        super(description);
        if (fromFile) {
            this.endDate = LocalDateTime.parse(endDate, this.outputDateTimeFormatter);
        } else {
            this.endDate = LocalDateTime.parse(endDate, this.inputDateTimeFormatter);
        }
    }

    @Override
    public String toEncodedString() {
        return "[" + TaskType.D + "]" + super.toString() + " /by " + this.endDate.format(this.outputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: "
                + this.endDate.format(this.outputDateTimeFormatter) + ")";
    }
}

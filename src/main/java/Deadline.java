import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime endDate;
    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = LocalDateTime.parse(endDate, this.inputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: "
                + this.endDate.format(this.outputDateTimeFormatter) + ")";
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String des, String deadline) throws InvalidDateException {
        super(des);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    @Override
    public String getStatusIcon() {
        return String.format("[D]%s | BY: %s", super.getStatusIcon(), this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String encode() {
        return String.format("%s ### %s ### %s", "deadline", super.encode(), this.deadline);
    }

    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.deadline.equals(date);
    }
}
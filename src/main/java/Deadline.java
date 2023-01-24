import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description);
        this.dueDateTime = handleDateTime(by);
    }

    public static Deadline createDeadline(String desc) throws InvalidDateTimeException {
        String[] deadlineArr = desc.split(" /by ");
        String deadlineDesc = deadlineArr[0].trim();
        String by = deadlineArr[1];
        return new Deadline(deadlineDesc, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                dueDateTime.toLocalDate(), dueDateTime.toLocalTime());
    }
}

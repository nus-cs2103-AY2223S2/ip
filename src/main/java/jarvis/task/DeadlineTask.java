package jarvis.task;

import jarvis.exception.CommandParseException;
import jarvis.exception.InvalidParameterException;
import jarvis.exception.MissingParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDate deadline;

    public DeadlineTask(String description, String deadline) throws CommandParseException {
        this(description, deadline, false);
    }

    public DeadlineTask(String description, String deadline, boolean isDone) throws CommandParseException {
        super(description, isDone);
        if (deadline == null || deadline.isBlank()) {
            throw new MissingParameterException("Missing deadline", "A deadline ('/by ...') is needed.");
        }
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid deadline", "I don't understand the given date.");
        }
    }

    @Override
    public boolean satisfies(TaskFilter filter) {
        if (filter == null || filter.isEmpty()) return true;
        boolean isAfter = filter.getAfterDate() == null || !filter.getAfterDate().isAfter(this.deadline);
        boolean isBefore = filter.getBeforeDate() == null || !filter.getBeforeDate().isBefore(this.deadline);
        return isAfter && isBefore;
    }

    @Override
    public String serialize() {
        String[] data = {"D", String.valueOf(this.isDone()), this.getDescription(), String.valueOf(this.deadline)};
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}

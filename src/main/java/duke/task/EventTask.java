package duke.task;

import duke.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends GeneralDukeTask{
    private final LocalDate from;
    private final LocalDate to;

    public EventTask(String info, LocalDate from, LocalDate to) throws InvalidInputException {
        super(info, TaskType.EVENT);
        this.from = from;
        this.to = to;
        if (from.isAfter(to)) {
            throw new InvalidInputException("☹ OOPS!!! Start date can not be after than the End date");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

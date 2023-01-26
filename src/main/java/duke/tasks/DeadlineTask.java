package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDate deadline;
    private static final String INDICATOR = "[Deadline]";

    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating Deadline duke.tasks.Task";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for Deadline";

    static final String DEADLINE_PREFIX_REPLACEMENT = "BY: ";

    DeadlineTask(String name, LocalDate deadline) throws DukeException {
        super(name);
        this.deadline = deadline;
    }


    public static DeadlineTask createTask(String name, String deadline) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return new DeadlineTask(name, date);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_EXCEPTION);
        }
    }


    private static String formattedDeadline (LocalDate deadline) {
        String formattedDate = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, formattedDate);
    }
    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedDeadline(deadline);
    }
}

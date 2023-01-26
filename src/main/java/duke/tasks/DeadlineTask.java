package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class DeadlineTask extends Task {
    static final String DEADLINE_PREFIX_REPLACEMENT = "BY: ";
    private static final String INDICATOR = "[Deadline]";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for Deadline";

    private final LocalDate deadline;

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


    private static String formattedDeadline(LocalDate deadline) {
        String formattedDate = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, DEADLINE_PREFIX_REPLACEMENT + formattedDate);
    }
    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedDeadline(deadline);
    }
}

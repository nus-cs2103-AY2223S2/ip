package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDate deadline;
    private static final String INDICATOR = "[Deadline]";
    static final String INPUT_PREFIX = "deadline ";
    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating Deadline duke.Task";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for Deadline";
    static final String DEADLINE_PREFIX = "/by ";
    static final String DEADLINE_PREFIX_REPLACEMENT = "BY: ";

    DeadlineTask(String name, LocalDate deadline) throws DukeException {
        super(name);
        this.deadline = deadline;
    }


    static DeadlineTask createDeadline(String text) throws DukeException {
        int prefixIndex = text.indexOf(DEADLINE_PREFIX);
        if (prefixIndex == -1) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
        try {
            String taskName = text.substring(INPUT_PREFIX.length(), prefixIndex - 1);
            String deadline = text.substring(prefixIndex);
            LocalDate date = LocalDate.parse(deadline);
            return new DeadlineTask(taskName, date);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
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

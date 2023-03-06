package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Creates the Parser class.
 */
public class Parser {

    /**
     * Checks if string is valid LocalDate and converts it.
     *
     * @param string User input.
     * @return Valid LocalDate.
     * @throws DukeException If user input does not match any DateTimeFormatter.
     */
    public static LocalDate stringToDate(String string) throws DukeException {
        LocalDate date = null;
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("ddMMyyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };
        for (DateTimeFormatter formatter : formatters) {
            // Goes through list of formatters to see which matches the date input
            try {
                date = LocalDate.parse(string, formatter);
                break;
            } catch (DateTimeParseException e) {
                // Invalid format, try the next one
            }
        }
        // If date is still null, input is in invalid format
        if (date == null) {
            throw new DukeException("Reenter dates in this format: (ddMMyyyy)");
        }
        return date;
    }

    /**
     * Checks if string is valid LocalDateTime and converts it.
     *
     * @param string User input.
     * @return Valid LocalDateTime.
     * @throws DukeException If user input does not match any DateTimeFormatter.
     */
    public static LocalDateTime stringToDateTime(String string, boolean isStart) throws DukeException {
        LocalDate date = null;
        LocalDateTime dateTime = null;
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("ddMMyyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("ddMMyyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        for (DateTimeFormatter formatter : formatters) {
            // Goes through list of formatters to see which matches the deadline date input
            try {
                dateTime = LocalDateTime.parse(string, formatter);
                break;
            } catch (DateTimeParseException e) {
                try {
                    date = LocalDate.parse(string, formatter);
                    break;
                } catch (DateTimeParseException e2) {
                    // Invalid format, try the next one
                }
            }
        }
        // If date and dateTime is still null, input is in invalid format
        if (date == null && dateTime == null) {
            throw new DukeException("Reenter date in this format: (ddMMyyyy) or (ddMMyyyy HHmm).");
        }

        // Converts date to include time
        if (date != null) {
            if (isStart) {
                dateTime = date.atStartOfDay();

            } else {
                dateTime = date.atStartOfDay().plusDays(1).minusNanos(1);
            }
        }
        return dateTime;
    }

    /**
     * @param parts User input.
     * @return An AddCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseTodoCommand(String[] parts) throws DukeException {
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("Command todo has to be followed by a description.");
        }
        Todo todo = new Todo(parts[1].trim());
        return new Command.AddCommand(todo);
    }

    /**
     * @param parts User input.
     * @return An AddCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseDeadlineCommand(String[] parts) throws DukeException {
        String deadlineError = "Command deadline has to have a string and date separated with a /by keyword.";
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DukeException(deadlineError);
        }
        String[] subparts = parts[1].split(" /by ", 2);
        if (subparts.length < 2 || subparts[0].trim().isEmpty() || subparts[1].trim().isEmpty()) {
            throw new DukeException(deadlineError);
        }
        LocalDateTime deadlineDateTime = stringToDateTime(subparts[1].trim(), false);
        Deadline deadline = new Deadline(subparts[0].trim(), deadlineDateTime);
        return new Command.AddCommand(deadline);
    }

    /**
     * @param parts User input.
     * @return An AddCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseEventCommand(String[] parts) throws DukeException {
        String eventError = "Command event has to have a string and 2 dates separated with /from and /to keywords.";
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DukeException(eventError);
        }
        String[] subparts = parts[1].split(" /from | /to ", 3);
        if (subparts.length < 3 || subparts[0].trim().isEmpty()
                || subparts[1].trim().isEmpty() || subparts[2].trim().isEmpty()) {
            throw new DukeException(eventError);
        }
        LocalDateTime startDateTime = stringToDateTime(subparts[1].trim(), true);
        LocalDateTime endDateTime = stringToDateTime(subparts[2].trim(), false);
        // Checks if start date is before end date
        if (endDateTime.isBefore(startDateTime)) {
            throw new DukeException("End date cannot be before start date.");
        }
        Event event = new Event(subparts[0].trim(), startDateTime, endDateTime);
        return new Command.AddCommand(event);
    }

    public static Command parseListCommand() {
        return new Command.ListCommand();
    }

    /**
     * @param parts User input.
     * @return A MarkCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseMarkCommand(String[] parts) throws DukeException {
        int indexInput;
        String markError = "Command mark has to be followed by an integer.";
        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
            throw new DukeException(markError);
        }
        try {
            indexInput = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new DukeException(markError);
        }
        return new Command.MarkCommand(indexInput - 1);
    }

    /**
     * @param parts User input.
     * @return An UnmarkCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseUnmarkCommand(String[] parts) throws DukeException {
        int indexInput;
        String unmarkError = "Command unmark has to be followed by an integer.";
        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
            throw new DukeException(unmarkError);
        }
        try {
            indexInput = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new DukeException(unmarkError);
        }
        return new Command.UnmarkCommand(indexInput - 1);
    }

    /**
     * @param parts User input.
     * @return A DeleteCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseDeleteCommand(String[] parts) throws DukeException {
        int indexInput;
        String deleteError = "Command delete has to be followed by an integer.";
        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
            throw new DukeException(deleteError);
        }
        try {
            indexInput = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new DukeException(deleteError);
        }
        return new Command.DeleteCommand(indexInput - 1);
    }

    /**
     * @param parts User input.
     * @return A FilterCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseFilterCommand(String[] parts) throws DukeException {
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("Command filter has to be followed by a keyword(s) separated with commas.");
        }
        String[] stringKeywords = parts[1].trim().split(",");
        String[] validKeywords = new String[stringKeywords.length];
        for (int i = 0; i < stringKeywords.length; i++) {
            validKeywords[i] = stringKeywords[i].trim();
        }
        return new Command.FilterCommand(validKeywords);
    }

    /**
     * @param parts User input.
     * @return A FilterDateCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseFilterDateCommand(String[] parts) throws DukeException {
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("Command filterdate has to be followed by date(s) separated with commas.");
        }
        String[] stringDates = parts[1].trim().split(",");
        LocalDate[] validDates = new LocalDate[stringDates.length];
        for (int i = 0; i < stringDates.length; i++) {
            validDates[i] = stringToDate(stringDates[i].trim());
        }
        return new Command.FilterDateCommand(validDates);
    }

    public static Command parseSortCommand() {
        return new Command.SortCommand();
    }

    public static Command parseSortDateCommand() {
        return new Command.SortDateCommand();
    }

    public static Command parseSortTaskCommand() {
        return new Command.SortTaskCommand();
    }

    public static Command parseSortDoneCommand() {
        return new Command.SortDoneCommand();
    }

    public static Command parseExitCommand() {
        return new Command.ExitCommand();
    }

    public static Command parseHelpCommand() {
        return new Command.HelpCommand();
    }

    /**
     * Parses the user input to match commands.
     *
     * @param fullCommand User input.
     * @return Command to execute with.
     * @throws DukeException If user input is invalid and do not match command requirements.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        switch (parts[0].trim().toLowerCase()) {
        case "todo":
            return parseTodoCommand(parts);
        case "deadline":
            return parseDeadlineCommand(parts);
        case "event":
            return parseEventCommand(parts);
        case "list":
            return parseListCommand();
        case "mark":
            return parseMarkCommand(parts);
        case "unmark":
            return parseUnmarkCommand(parts);
        case "delete":
            return parseDeleteCommand(parts);
        case "filter":
            return parseFilterCommand(parts);
        case "filterdate":
            return parseFilterDateCommand(parts);
        case "sort":
            return parseSortCommand();
        case "sortdate":
            return parseSortDateCommand();
        case "sorttask":
            return parseSortTaskCommand();
        case "sortdone":
            return parseSortDoneCommand();
        case "bye":
            return parseExitCommand();
        case "help":
            return parseHelpCommand();
        default:
            throw new DukeException("I can't do that for you...\n(Enter 'help' to show guide.)");
        }
    }
}

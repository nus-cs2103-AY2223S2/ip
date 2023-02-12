package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * Parses the user input to match commands.
     *
     * @param fullCommand User input.
     * @return Command to execute with.
     * @throws DukeException If user input is invalid and do not match command requirements.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        int indexInput;
        switch(parts[0].trim().toLowerCase()) {
            case "todo":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("Command todo has to be followed by a description.");
                }
                Todo todo = new Todo(parts[1].trim());
                return new Command.AddCommand(todo);
            case "deadline":
                String deadlineError = "Command deadline has to have a string and date separated with a /by keyword.";
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException(deadlineError);
                }
                String[] d_parts = parts[1].split(" /by ", 2);
                if (d_parts.length < 2 || d_parts[0].trim().isEmpty() || d_parts[1].trim().isEmpty()) {
                    throw new DukeException(deadlineError);
                }
                LocalDateTime deadlineDateTime = stringToDateTime(d_parts[1].trim(),false);
                Deadline deadline = new Deadline(d_parts[0].trim(), deadlineDateTime);
                return new Command.AddCommand(deadline);
            case "event":
                String eventError = "Command event has to have a string and 2 dates separated with /from and /to keywords.";
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException(eventError);
                }
                String[] e_parts = parts[1].split(" /from | /to ", 3);
                if (e_parts.length < 3 || e_parts[0].trim().isEmpty() || e_parts[1].trim().isEmpty() || e_parts[2].trim().isEmpty()) {
                    throw new DukeException(eventError);
                }
                LocalDateTime startDateTime = stringToDateTime(e_parts[1].trim(),true);
                LocalDateTime endDateTime = stringToDateTime(e_parts[2].trim(),false);
                // Checks if start date is before end date
                if (endDateTime.isBefore(startDateTime)) {
                    throw new DukeException("End date cannot be before start date.");
                }
                Event event = new Event(e_parts[0].trim(), startDateTime, endDateTime);
                return new Command.AddCommand(event);
            case "list":
                return new Command.ListCommand();
            case "mark":
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
            case "unmark":
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
            case "delete":
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
            case "filter":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("Command filter has to be followed by a keyword(s) separated with commas.");
                }
                String[] stringKeywords = parts[1].trim().split(",");
                String[] validKeywords = new String[stringKeywords.length];
                for (int i = 0; i < stringKeywords.length; i++) {
                    validKeywords[i] = stringKeywords[i].trim();
                }
                return new Command.FilterCommand(validKeywords);
            case "filterdate":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("Command filterdate has to be followed by date(s) separated with commas.");
                }
                String[] stringDates = parts[1].trim().split(",");
                LocalDate[] validDates = new LocalDate[stringDates.length];
                for (int i = 0; i < stringDates.length; i++) {
                    validDates[i] = stringToDate(stringDates[i].trim());
                }
                return new Command.FilterDateCommand(validDates);
            case "sort":
                return new Command.SortCommand();
            case "sortdate":
                return new Command.SortDateCommand();
            case "sorttask":
                return new Command.SortTaskCommand();
            case "sortdone":
                return new Command.SortDoneCommand();
            case "bye":
                return new Command.ExitCommand();
            case "help":
                return new Command.HelpCommand();
            default:
                throw new DukeException("I can't do that for you...\n(Enter 'help' to show guide.)");
        }
    }
}

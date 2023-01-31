package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

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
        switch(parts[0].trim()) {
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
                Deadline deadline = new Deadline(d_parts[0].trim(), d_parts[1].trim());
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
                Event event = new Event(e_parts[0].trim(), e_parts[1].trim(), e_parts[2].trim());
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
            case "filterdate":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("Command filterdate has to be followed by date.");
                }
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
                        date = LocalDate.parse(parts[1].trim(), formatter);
                        break;
                    } catch (DateTimeParseException e) {
                        // Invalid format, try the next one
                    }
                }
                if (date == null) {
                    throw new DukeException("Reenter date in this format: (ddMMyyyy)");
                }
                return new Command.FilterCommand(date);
            case "filter":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("Command filter has to be followed by a keyword.");
                }
                String keyword = parts[1].trim();
                return new Command.FilterCommand(keyword);
            case "bye":
                return new Command.ExitCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

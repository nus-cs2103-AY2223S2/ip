package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        int indexInput;
        switch(parts[0].trim()) {
            case "todo":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Todo todo = new Todo(parts[1].trim());
                return new Command.AddCommand(todo);
            case "deadline":
                String deadlineError = "A deadline has to have a string and date separated with a /by keyword.";
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
                String eventError = "An event has to have a string and 2 dates separated with /from and /to keywords.";
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
                if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                    throw new DukeException("duke.command.Command has to be followed by an integer.");
                }
                try {
                    indexInput = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("duke.command.Command has to be followed by an integer.");
                }
                return new Command.MarkCommand(indexInput - 1);
            case "unmark":
                if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                    throw new DukeException("duke.command.Command has to be followed by an integer.");
                }
                try {
                    indexInput = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("duke.command.Command has to be followed by an integer.");
                }
                return new Command.UnmarkCommand(indexInput - 1);
            case "delete":
                if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                    throw new DukeException("duke.command.Command has to be followed by an integer.");
                }
                try {
                    indexInput = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("duke.command.Command has to be followed by an integer.");
                }
                return new Command.DeleteCommand(indexInput - 1);
            case "filterdate":
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("duke.command.Command has to be followed by date.");
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
            case "bye":
                return new Command.ExitCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
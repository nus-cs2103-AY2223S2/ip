package duke;

import static duke.utils.FormatHelper.INPUTFORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

/**
 * Parser class that processes user inputs.
 */
public class Parser {
    /**
     * Parses user command input and returns an appropriate Command object.
     * @param userInput text command that the user inputted.
     * @return Command object representing the type of command.
     * @throws DukeException If user inputted command is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("")) {
            return new EmptyCommand();
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.matches("mark \\d+")) {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            return new MarkCommand(index);
        } else if (userInput.matches("unmark \\d+")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            return new UnmarkCommand(index);
        } else if (userInput.matches("^todo .*")) {
            String description = userInput.substring(5);
            return new AddTodoCommand(description);
        } else if (userInput.matches("^deadline .*")) {
            int byPos = userInput.indexOf(" /by ");
            if (byPos == -1) {
                throw new DukeException("Deadline not specified with /by");
            }
            String description = userInput.substring(9, byPos);
            String by = userInput.substring(byPos + 5);
            LocalDateTime convertedBy;
            try {
                convertedBy = LocalDateTime.parse(by, INPUTFORMAT);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date and Time provided, use the format: dd/MM/yyyy HH:mm");
            }
            return new AddDeadlineCommand(description, convertedBy);
        } else if (userInput.matches("^event .*")) {
            int fromPos = userInput.indexOf(" /from ");
            int toPos = userInput.indexOf(" /to ");
            if (fromPos == -1 || toPos == -1 || toPos > userInput.length() + 4) {
                throw new DukeException("Please include both /from and /to");
            }
            if (fromPos > toPos) {
                throw new DukeException("Please add the from date first followed by to date");
            }
            if (fromPos == 5) {
                throw new DukeException("Please include a description of the task");
            }
            String description = userInput.substring(6, fromPos);
            String from = userInput.substring(fromPos + 7, toPos);
            String to = userInput.substring(toPos + 5);
            LocalDateTime convertedFrom;
            LocalDateTime convertedTo;
            try {
                convertedFrom = LocalDateTime.parse(from, INPUTFORMAT);
                convertedTo = LocalDateTime.parse(to, INPUTFORMAT);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date and Time provided, use the format: dd/MM/yyyy HH:mm");
            }
            return new AddEventCommand(description, convertedFrom, convertedTo);
        } else if (userInput.matches("^delete \\d+")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            return new DeleteCommand(index);
        } else if (userInput.matches("^find .*")) {
            String keyword = userInput.substring(5);
            return new FindCommand(keyword);
        } else {
            return new UnknownCommand();
        }
    }
}

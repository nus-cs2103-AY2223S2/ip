package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.data.exception.DukeException;

/**
 * Parses command string and initialises the appropriate command
 */
public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] cmdDetails = fullCommand.split(" ", 2);
        String cmd = cmdDetails[0];
        String details = "";
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }
        int len = cmdDetails.length;
        if (len > 1) {
            details = cmdDetails[1];
        }
        if (cmd.equals("mark")) {
            if (len == 1) {
                throw new DukeException("Unable to mark.");
            }
            return new MarkCommand(details);
        } else if (cmd.equals("unmark")) {
            if (len == 1) {
                throw new DukeException("Unable to unmark.");
            }
            return new UnmarkCommand(details);
        } else if (cmd.equals("todo")) {
            if (len == 1) {
                throw new DukeException("Description of todo cannot be empty.");
            }
            return new AddTodoCommand(details);
        } else if (cmd.equals("deadline")) {
            String[] temp = details.split(" /by ");
            if (len == 1 || temp.length != 2) {
                throw new DukeException("Description of deadline cannot be incomplete.");
            }
            return new AddDeadlineCommand(temp[0], temp[1]);
        } else if (cmd.equals("event")) {
            String[] temp = details.split(" /from ");
            if (len == 1 || temp.length != 2) {
                throw new DukeException("Description of event cannot be incomplete.");
            }
            String[] temp2 = temp[1].split(" /to ");
            if (temp2.length != 2) {
                throw new DukeException("Description of event cannot be incomplete.");
            }
            return new AddEventCommand(temp[0], temp2[0], temp2[1]);
        } else if (cmd.equals("delete")) {
            if (len == 1) {
                throw new DukeException("Unable to delete.");
            }
            return new DeleteCommand(details);
        } else {
            throw new DukeException("I do not quite understand that.");
        }
    }
}


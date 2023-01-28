package duke.parser;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

/**
 * Encapsulates the System.in parser of <code>Duke</code>.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Parser {
    /**
     * Parsers the user's input to return the respective <code>Command</code>.
     * @param fullCommand A <code>String</code> of the user's full input.
     * @return The <code>Command</code> corresponding to what the user gave.
     */
    public static Command parse(String fullCommand) {
        String[] splitted = fullCommand.split(" ");
        switch (splitted[0]) {

        case "list":
            Command listC = new ListCommand(fullCommand);
            return listC;

        case "mark":
            Command markC = new MarkCommand(fullCommand);
            return markC;

        case "unmark":
            Command unmarkC = new UnmarkCommand(fullCommand);
            return unmarkC;

        case "todo":
            Command todoC = new TodoCommand(fullCommand);
            return todoC;

        case "deadline":
            Command deadlineC = new DeadlineCommand(fullCommand);
            return deadlineC;

        case "event":
            Command eventC = new EventCommand(fullCommand);
            return eventC;

        case "delete":
            Command deleteC = new DeleteCommand(fullCommand);
            return deleteC;

        case "bye":
            Command byeC = new ByeCommand(fullCommand);
            return byeC;

        case "clear":
            Command clearC = new ClearCommand(fullCommand);
            return clearC;

        case "find":
            Command findC = new FindCommand(fullCommand);
            return findC;

        default:
            Command unknownC = new UnknownCommand(fullCommand);
            return unknownC;
        }

    }
}

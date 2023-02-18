package reborn.parser;

import reborn.command.AddDeadlineCommand;
import reborn.command.AddEventCommand;
import reborn.command.AddTodoCommand;
import reborn.command.Command;
import reborn.command.DeleteCommand;
import reborn.command.ExitCommand;
import reborn.command.FindCommand;
import reborn.command.ListCommand;
import reborn.command.MarkCommand;
import reborn.command.UnmarkCommand;
import reborn.data.exception.RebornException;

/**
 * Represents command parser.
 */
public class Parser {

    /**
     * Parses given command, initialising appropriate command.
     *
     * @param fullCommand String of full command.
     * @return Command initialised as appropriate.
     * @throws RebornException If invalid commands.
     */
    public static Command parse(String fullCommand) throws RebornException {
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
                throw new RebornException("Can't mark.");
            }
            return new MarkCommand(details);
        } else if (cmd.equals("unmark")) {
            if (len == 1) {
                throw new RebornException("Can't unmark.");
            }
            return new UnmarkCommand(details);
        } else if (cmd.equals("todo")) {
            if (len == 1) {
                throw new RebornException("Description of todo can't be empty.");
            }
            return new AddTodoCommand(details);
        } else if (cmd.equals("deadline")) {
            String[] temp = details.split(" /by ");
            if (len == 1 || temp.length != 2) {
                throw new RebornException("Description of deadline can't be incomplete.");
            }
            return new AddDeadlineCommand(temp[0], temp[1]);
        } else if (cmd.equals("event")) {
            String[] temp = details.split(" /from ");
            if (len == 1 || temp.length != 2) {
                throw new RebornException("Description of event can't be incomplete.");
            }
            String[] temp2 = temp[1].split(" /to ");
            if (temp2.length != 2) {
                throw new RebornException("Description of event can't be incomplete.");
            }
            return new AddEventCommand(temp[0], temp2[0], temp2[1]);
        } else if (cmd.equals("delete")) {
            if (len == 1) {
                throw new RebornException("Can't delete.");
            }
            return new DeleteCommand(details);
        } else if (cmd.equals("find")) {
            return new FindCommand(details);
        } else {
            throw new RebornException("Can you please make some sense?");
        }
    }
}


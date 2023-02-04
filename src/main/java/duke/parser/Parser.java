package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * This class allows commands to be stored and checked, whether they are in the system
 * and its operations can be carried out.
 */
public class Parser {
    private enum CommandType {
        todo,
        deadline,
        mark,
        unmark,
        list,
        bye,
        delete,
        find,
        event;
    }

    /**
     * This method parses the string input and breaks it down to its respective inputs, messages, dates.
     *
     * @param input String input, where they can indicate "todo work" etc, which is considered an input.
     * @return Command - A command object of the indicated type will be returned
     * @throws IllegalArgumentException - Error that the command does not exists.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandSeparated = input.split(" ");
        CommandType typeOfCommand = CommandType.valueOf(commandSeparated[0].toLowerCase());
        switch (typeOfCommand) {
        case todo:
            return new TodoCommand(input);

        case deadline:
            return new DeadlineCommand(input);

        case mark:
            return new MarkCommand(commandSeparated[1]);

        case unmark:
            return new UnmarkCommand(commandSeparated[1]);

        case list:
            return new ListCommand();

        case bye:
            return new ByeCommand();

        case delete:
            return new DeleteCommand(commandSeparated[1]);

        case event:
            return new EventCommand(input);

        case find:
            return new FindCommand(input);

        default:
            throw new DukeException("Error has Occured.");
        }
    }

}

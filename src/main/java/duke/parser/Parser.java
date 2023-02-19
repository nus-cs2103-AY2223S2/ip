package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the user input.
     * @param fullCommand the user input.
     * @return Command that executes the required action according to the user input.
     * @throws DukeException if the user input cannot be parsed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String commandKeyword;
        String restOfCommand = "";
        if (fullCommand.contains(" ")) {
            commandKeyword = fullCommand.substring(0, fullCommand.indexOf(" "));
            restOfCommand = fullCommand.substring(fullCommand.indexOf(" ") + 1);
        } else {
            commandKeyword = fullCommand;
        }
        Command command;
        switch (commandKeyword) {
        case "bye": {
            command = new ByeCommand();
            break;
        }
        case "list": {
            command = new ListCommand();
            break;
        }
        case "mark": {
            command = new MarkCommand(restOfCommand);
            break;
        }
        case "unmark": {
            command = new UnmarkCommand(restOfCommand);
            break;
        }
        case "todo":
        case "deadline":
        case "event": {
            command = new AddCommand(commandKeyword, restOfCommand);
            break;
        }
        case "delete": {
            command = new DeleteCommand(restOfCommand);
            break;
        }
        case "find": {
            command = new FindCommand(restOfCommand);
            break;
        }
        case "update": {
            command = new UpdateCommand(restOfCommand);
            break;
        }
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}

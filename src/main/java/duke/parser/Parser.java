package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

public class Parser {
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
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}

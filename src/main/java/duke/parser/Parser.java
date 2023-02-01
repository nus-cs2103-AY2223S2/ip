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
        String command;
        String restOfString = "";
        if (fullCommand.contains(" ")) {
            command = fullCommand.substring(0, fullCommand.indexOf(" "));
            restOfString = fullCommand.substring(fullCommand.indexOf(" ") + 1);
        } else {
            command = fullCommand;
        }
        Command c;
        switch (command) {
            case "bye": {
                c = new ByeCommand();
                break;
            }
            case "list": {
                c = new ListCommand();
                break;
            }
            case "mark": {
                c = new MarkCommand(restOfString);
                break;
            }
            case "unmark": {
                c = new UnmarkCommand(restOfString);
                break;
            }
            case "todo":
            case "deadline":
            case "event": {
                c = new AddCommand(command, restOfString);
                break;
            }
            case "delete": {
                c = new DeleteCommand(restOfString);
                break;
            }
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }
}

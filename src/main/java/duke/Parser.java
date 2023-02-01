package duke;

import duke.commands.Command;
import duke.exceptions.DukeUnknownInputException;
import duke.commands.*;

public class Parser {
    public Parser() {}

    public Command parse(String fullCommand) throws DukeUnknownInputException {
        String[] commands = fullCommand.split(" ", 2);
        String command = commands[0];
        String params;
        if (commands.length != 2) {
            params = "";
        } else {
            params = commands[1];
        }
        switch (command) {
        case "list":
            return new ListCommand(params);
        case "mark":
            return new MarkCommand(params);
        case "unmark":
            return new UnmarkCommand(params);
        case "todo":
            return new TodoCommand(params);
        case "deadline":
            return new DeadlineCommand(params);
        case "event":
            return new EventCommand(params);
        case "delete":
            return new DeleteCommand(params);
        case "bye":
            return new ExitCommand(params);
        case "find":
            return new FindCommand(params);
        default:
            throw new DukeUnknownInputException();
        }
    }
}

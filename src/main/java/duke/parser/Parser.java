package duke.parser;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;
import duke.command.UnknownCommand;

public class Parser {

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
            return  byeC;

        default:
            Command unknownC = new UnknownCommand(fullCommand);
            return unknownC;
        }

    }
}

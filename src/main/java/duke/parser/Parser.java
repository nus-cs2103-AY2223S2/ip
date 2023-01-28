package duke.parser;

import duke.command.*;

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

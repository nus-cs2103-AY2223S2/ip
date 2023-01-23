package sebastian.main;

import sebastian.command.*;
import sebastian.sebastianExceptions.IllegalInputException;

public class Parser {

    public static Command parse(String fullCommand) throws IllegalInputException {
        String action = fullCommand.split(" ")[0];
        switch (action) {
            case "find":
                return new FindCommand(fullCommand);
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(fullCommand);
            case "unmark":
                return new UnmarkCommand(fullCommand);
            case "todo":
               return new AddTodoCommand(fullCommand);
            case "deadline":
                return new AddDeadlineCommand(fullCommand);
            case "event":
               return new AddEventCommand(fullCommand);
            case "delete" :
               return new DeleteCommand(fullCommand);
            case "get" :
                return new GetCommand(fullCommand);
            case "bye" :
                return new ExitCommand();
            default:
                throw new IllegalInputException();
        }
    }
}

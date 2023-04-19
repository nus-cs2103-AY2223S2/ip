package duke.parser;

import duke.command.*;
import duke.exception.DukeException;


public class Parser {

    private static String keyword;
    private static String statement;

    /**
     * Parse the user's input into formatted command.
     * @param fullCommand a single user's command line.
     */
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        if (countWithSplit(fullCommand) == 0) throw new DukeException();
        else if (countWithSplit(fullCommand) == 1){
            keyword = fullCommand;
            switch (keyword) {
                case "list":
                    return new ListCommand(keyword, statement);
                case "bye":
                    return new ByeCommand();
                default:
                    throw new DukeException();
            }
        }
        else {
            keyword = fullCommand.split("\\s+")[0];
            keyword = keyword.toLowerCase();
            statement = fullCommand.replaceFirst(keyword + "\\s+", "");
            switch (keyword) {
                case "mark":
                    return new MarkCommand(keyword, statement);
                case "unmark":
                    return new UnmarkCommand(keyword, statement);
                case "todo":
                    return new TodoCommand(keyword, statement);
                case "deadline":
                    return new DeadlineCommand(keyword, statement);
                case "event":
                    return new EventCommand(keyword, statement);
                case "find":
                    return new FindCommand(statement);
                case "delete":
                    return new DeleteCommand(keyword, statement);
                default:
                    throw new DukeException();
            }
        }
    }

    public static int countWithSplit(String str) {
        if (isNullOrBlank(str)) {
            return 0;
        }
        return str.split("\\s+").length;
    }

    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }
}

package duke;

/**
 * A parser class to make sense of the user command.
 */
public class Parser {

    /**
     * Constructs the parser.
     */
    public Parser() {
        //
    }

    /**
     * Parses the input command from the user.
     *
     * @param input Input command from user.
     * @return Command to be executed.
     * @throws DukeException If error occurs.
     */
    public static Command parse(String input) throws DukeException{
        try {
            String[] inputSplit = input.split(" ");
            switch (inputSplit[0]) {
            case "todo":
                return new TodoCommand(input.replaceFirst("todo", "").trim());
            case "deadline":
                return new DeadlineCommand(input.replaceFirst("deadline", "").trim());
            case "event":
                return new EventCommand(input.replaceFirst("event", "").trim());
            case "list":
                return new ListCommand(input.replaceFirst("list", "").trim());
            case "delete":
                return new DeleteCommand(input.replaceFirst("delete", "").trim());
            case "mark":
                return new MarkCommand(input.replaceFirst("mark", "").trim());
            case "unmark":
                return new UnmarkCommand(input.replaceFirst("unmark", "").trim());
            case "bye":
                return new ByeCommand(input.replaceFirst("bye", "").trim());
            case "find":
                return new FindCommand(input.replaceFirst("find", "").trim());
            case "sort":
                return new SortCommand(input.replaceFirst("sort", "").trim());
            default:
                throw new DukeException("No such command!");
            }
        } catch (DukeException e) {
            throw new DukeException("Something went wrong! Please try again.");
        }
    }

}

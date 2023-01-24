package duke;

public class Parser {
    public static Command parse (String input) throws DukeUnknownCommandException,
            DukeEmptyArgumentException, DukeInvalidArgumentException {
        String[] split = input.split(" ", 2);
        String cmd = split[0];
        Command c;

        switch (cmd) {
            case "exit":
                c = new ExitCommand();
                break;
            case "list":
                c = new ListCommand();
                break;
            case "delete":
                c = new DeleteCommand(split);
                break;
            case "mark":
                c = new MarkCommand(split);
                break;
            case "unmark":
                c = new UnmarkCommand(split);
                break;
            case "todo":
            case "deadline":
            case "event":
                c = new AddCommand(split);
                break;
            default:
                throw new DukeUnknownCommandException(
                        "I'm sorry, but I don't know what that means :-(");
        }

        return c;
    }
}

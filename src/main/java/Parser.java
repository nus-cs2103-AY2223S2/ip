
/**
 * Deals with making sense of the user command
 */
public class Parser {

    protected enum COMMAND {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE};

    public Parser() {

    }

    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] input = fullCommand.trim().split(" ", 2);
            COMMAND cmd = COMMAND.valueOf(input[0].toUpperCase());

            switch (cmd) {
            case BYE:
                return new exitCommand();
            case LIST:
                return new listCommand();
            case TODO:
                return new addCommand(Todo.generate(fullCommand));
            case DEADLINE:
                return new addCommand(Deadline.generate(fullCommand));
            case EVENT:
                return new addCommand(Event.generate(fullCommand));
            case MARK:
                return new markCommand(fullCommand);
            case UNMARK:
                return new unmarkCommand(fullCommand);
            case DELETE:
                return new deleteCommand(fullCommand);
            default:
                throw new DukeException("");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("");
        }
    }
}

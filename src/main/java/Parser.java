/**
 * A class to parse commands received from user input.
 */
public class Parser {
    /** Same basic instances as Duke */
    private Ui ui;
    private Storage storage;
    private TaskList list;

    /** Check for program exit (true when bye command entered).*/
    private boolean isExit;

    /**
     * Constructor for Parser class.
     * @param ui The Ui reference of Duke.
     * @param storage The Storage reference of Duke.
     * @param list The TaskList reference of Duke.
     */
    public Parser(Ui ui, Storage storage, TaskList list) {
        this.ui = ui;
        this.storage = storage;
        this.list = list;
        isExit = false;
    }

    /**
     * Parses a command received from input.
     * @param commandStr command to be parsed.
     * @return The Command obtained from parsing.
     * @throws DukeException If command input is invalid.
     */
    public Command parse(String commandStr) throws DukeException{
        switch (commandStr.split("\\s+")[0]) {
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "delete":
            return new DeleteCommand();
        case "bye":
            isExit = true;
            return new ByeCommand();
        default:
            throw new DukeException("Invalid command");
        }
    }

    public boolean getIsExit() {
        return isExit;
    }
}

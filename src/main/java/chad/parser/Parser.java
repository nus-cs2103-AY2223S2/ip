package chad.parser;

import chad.command.AddDeadlineCommand;
import chad.command.AddEventCommand;
import chad.command.AddTodoCommand;
import chad.command.Command;
import chad.command.ExitCommand;
import chad.command.FindCommand;
import chad.command.ListCommand;
import chad.command.MarkCommand;
import chad.command.RemoveCommand;
import chad.command.UnmarkCommand;
import chad.exception.UnknownCommandException;
import chad.storage.LocalStorage;
import chad.storage.TaskList;

/**
 * Class to parse user's request and perform user's desired action.
 */
public class Parser {

    private final String request;
    private final TaskList tasks;
    private String response;
    private LocalStorage localStorage;

    /**
     * Enum for commands.
     */
    protected static enum CommandType {
        BYE, DEADLINE, DELETE, EVENT, FIND, LIST, MARK, UNMARK, TODO;

        /**
         * Find command type of on user's input
         * @param input User's input.
         * @return Command type.
         */
        public static CommandType findCommand(String input) {
            for (CommandType commandType : CommandType.values()) {
                if (commandType.name().equalsIgnoreCase(input)) {
                    return commandType;
                }
            }
            return null;
        }
    }

    /**
     * Constructor for Parser class
     * @param request request by the user
     */
    public Parser(String request) {
        this.request = request;
        this.tasks = new TaskList();
    }

    /**
     * Constructor for Parser class
     * @param request request by the user
     * @param tasks duke.task array to store the tasks added by the user
     */
    public Parser(String request, TaskList tasks, LocalStorage localStorage) {
        this.request = request;
        this.tasks = tasks;
        this.localStorage = localStorage;
    }

    /**
     * Function to process the user's request and get the response
     * @return Command to be executed.
     */
    public Command processRequest() {
        String[] req = this.request.split(" ");
        String command = req[0];
        CommandType commandType = CommandType.findCommand(command);

        if (commandType == null) {
            throw new UnknownCommandException();
        }

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(this.request);
        case UNMARK:
            return new UnmarkCommand(this.request);
        case TODO:
            return new AddTodoCommand(this.request);
        case DEADLINE:
            return new AddDeadlineCommand(this.request);
        case EVENT:
            return new AddEventCommand(this.request);
        case DELETE:
            return new RemoveCommand(this.request);
        case FIND:
            return new FindCommand(this.request);
        case BYE:
            return new ExitCommand(this.localStorage, this.tasks);
        default:
            assert false : "Should not reach here since unknown command exception is handled.";
            throw new UnknownCommandException();
        }
    }

    @Override
    public String toString() {
        return response;
    }
}

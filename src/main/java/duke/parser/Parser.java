package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RemoveCommand;
import duke.command.UnmarkCommand;
import duke.exception.UnknownCommandException;
import duke.storage.TaskList;

/**
 * Class to parse user's request and perform user's desired action.
 */
public class Parser {

    private final String request;
    private final TaskList tasks;
    private String response;

    /**
     * Constructor for Parser.Request class
     * @param request request by the user
     * @param tasks duke.task array to store the tasks added by the user
     */
    public Parser(String request, TaskList tasks) {
        this.request = request;
        this.tasks = tasks;
    }

    /**
     * Function to process the user's request and get the response
     * @return
     */
    public Command processRequest() {
        String[] req = this.request.split(" ");
        String command = req[0];
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(this.request);
        case "unmark":
            return new UnmarkCommand(this.request);
        case "todo":
            return new AddTodoCommand(this.request);
        case "deadline":
            return new AddDeadlineCommand(this.request);
        case "event":
            return new AddEventCommand(this.request);
        case "delete":
            return new RemoveCommand(this.request);
        case "find":
            return new FindCommand(this.request);
        default:
            throw new UnknownCommandException();
        }
    }

    @Override
    public String toString() {
        return response;
    }
}

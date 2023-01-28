package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.RemoveCommand;
import command.UnmarkCommand;
import dukeException.UnknownCommandException;
import storage.TaskList;

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
     * @param tasks task array to store the tasks added by the user
     */
    public Parser(String request, TaskList tasks) {
        this.request = request;
        this.tasks = tasks;
        processRequest();
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
            return new AddEventCommand(request);
        case "delete":
            return new RemoveCommand(request);
        case "find":
            return new FindCommand(request);
        default:
            throw new UnknownCommandException();
        }
    }

    @Override
    public String toString() {
        return response;
    }
}

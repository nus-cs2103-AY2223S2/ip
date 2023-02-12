package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * Parser to parse the command and process the necessary actions associated with the command.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructor for parser.
     * @param tasks TaskList of tasks for the Parser to retrieve the tasks and act on it accordingly.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user's input and execute the necessary actions associated with the command.
     * @param command
     * @param userInput The user input to be parsed to be used in the execution of its associated command.
     * @return a string that describes the action done.
     * @throws DukeException if there is missing required information in the user's input.
     */
    public String execute(Command command, String userInput) throws DukeException {
        switch (command) {
        case BYE:
            return ByeCommand.executeBye();
        case LIST:
            return ListCommand.executeList(tasks);
        case MARK:
            return MarkCommand.executeMark(tasks, userInput);
        case UNMARK:
            return UnmarkCommand.executeUnmark(tasks, userInput);
        case DELETE:
            return DeleteCommand.executeDelete(tasks, userInput);
        case TODO:
            return TodoCommand.addTodo(tasks, userInput);
        case DEADLINE:
            return DeadlineCommand.addDeadline(tasks, userInput);
        case EVENT:
            return EventCommand.addEvent(tasks, userInput);
        case GETEVENTSON:
            return GetEventsOnCommand.retrieveEvents(tasks, userInput);
        case FIND:
            return FindCommand.executeFind(tasks, userInput);
        default:
            assert false : "Unknown commands should have been resolved and not parsed.";
            return "";
        }
    }
}

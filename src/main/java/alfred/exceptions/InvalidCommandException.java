package alfred.exceptions;

/**
 * Represent an exception when the command has invalid formatting or is non-existential.
 */
public class InvalidCommandException extends AlfredException {

    private static final String INVALID_COMMAND = "I'm sorry, but I don't know what that means\n";

    /**
     * Constructs an InvalidCommandException object that contains the invalid command error message.
     * @param command The command given by the user.
     */
    public InvalidCommandException(String command) {
        super(getErrorMessage(command));
    }

    private static String getErrorMessage(String command) {
        switch (command) {
        case ("bye"):
            return "To exit the program type: bye";
        case ("list"):
            return "To list the set of tasks: list";
        case ("mark"):
            return "To mark a task: mark <task-index>";
        case ("unmark"):
            return "To unmark a task: unmark <task-index>";
        case ("delete"):
            return "To delete a task: delete <task-index>";
        case("todo"):
            return "To add a to ToDo task: todo <task-description>";
        case ("deadline"):
            return "To add a Deadline task: deadline <TaskName> /by <DueDate>";
        case ("event"):
            return "To add an Event task: event <EventName> /from <StartDate> /to <EndDate>";
        default:
            return INVALID_COMMAND;
        }
    }
}

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
            return "To exit the program type: bye\n";
        case ("list"):
            String message = "To list the set of tasks: list\n";
            message += "OR\n";
            message += "To list the set of tasks with a given date: list <date>\n";
            return message;
        case ("mark"):
            return "To mark a task: mark <task-index>\n";
        case ("unmark"):
            return "To unmark a task: unmark <task-index>\n";
        case ("delete"):
            return "To delete a task: delete <task-index>\n";
        case("todo"):
            return "To add a to ToDo task: todo <task-description>\n";
        case ("deadline"):
            return "To add a Deadline task: deadline <TaskName> /by <DueDate>\n";
        case ("event"):
            return "To add an Event task: event <EventName> /from <StartDate> /to <EndDate>\n";
        case ("help"):
            return "To get all the command: help\n";
        case ("find"):
            return "To find tasks with key words: find <key-word>\n";
        default:
            return INVALID_COMMAND;
        }
    }
}

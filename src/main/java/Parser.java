import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser: deals with making sense of the user command
 */
public class Parser {
    private static final String BYE_PATTERN = "bye";
    private static final String LIST_PATTERN = "list";
    private static final String MARK_PATTERN = "mark\\s+\\d*";
    private static final String UNMARK_PATTERN = "unmark\\s+\\d*";
    private static final String DELETE_PATTERN = "delete\\s+\\d*";
    private static final String TODO_PATTERN = "todo\\s+(.*)";
    private static final String DEADLINE_PATTERN = "deadline\\s+(.*)\\s+/by\\s+(.*)";
    private static final String EVENT_PATTERN = "event\\s+(.*)\\s+/from\\s+(.*)\\s+/to\\s+(.*)";

    public Parser() {

    }

    public static boolean isValidCommand(String command) {
        String[] commandsCollection = {BYE_PATTERN, LIST_PATTERN, MARK_PATTERN, UNMARK_PATTERN,
                DELETE_PATTERN, TODO_PATTERN, DEADLINE_PATTERN, EVENT_PATTERN};
        for (String pattern : commandsCollection) {
            if (command.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTaskCommand(String command) {
        String[] commandsCollection = {TODO_PATTERN, DEADLINE_PATTERN, EVENT_PATTERN};
        for (String pattern : commandsCollection) {
            if (command.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    public static Task toTask(String command) throws DukeException {
        Task task;
        if (Parser.isTaskCommand(command)) {
            if (command.matches(TODO_PATTERN)) {
                String taskDescription = command.substring(5);
                task = new Todo(taskDescription);
            } else if (command.matches(DEADLINE_PATTERN)) {
                String[] temp = command.substring(9).split(" /by ");
                String taskDescription = temp[0];
                String by = temp[1];
                task = new Deadline(taskDescription, by);
            } else { // (command.matches(EVENT_PATTERN)) {
                String[] temp = command.substring(6).split(" /from ");
                String taskDescription = temp[0];
                String[] time = temp[1].split(" /to ");
                String from = time[0];
                String to = time[1];
                task = new Event(taskDescription, from, to);
            }
        } else {
            throw new DukeException("☹ OOPS!!! You cannot convert a non-task command into a task.");
        }
        return task;
    }

    public static Integer indexToMark(String command) {
        return Integer.parseInt(command.substring(5));
    }

    public static Integer indexToUnmark(String command) {
        return Integer.parseInt(command.substring(7));
    }

    public static Integer indexToDelete(String command) {
        return Integer.parseInt(command.substring(7));
    }
}



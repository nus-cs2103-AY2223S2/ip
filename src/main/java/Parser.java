/**
 * File name: Parser.java
 * @author: Jerome Neo
 * Description: Parser class deals with making sense of the user command.
 */
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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

    /**
     * Returns a boolean that checks if the command matches the aforementioned command formats.
     *
     * @param command is the raw string input.
     * @return true if the command is recognised, else false.
     */
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

    /**
     * Returns a boolean that checks if the command is a Task command and it matches the aforementioned command formats.
     *
     * @param command is the raw string input.
     * @return true if the command is recognised as a Task, else false.
     */
    public static boolean isTaskCommand(String command) {
        String[] commandsCollection = {TODO_PATTERN, DEADLINE_PATTERN, EVENT_PATTERN};
        for (String pattern : commandsCollection) {
            if (command.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Task object if the command is compatible.
     *
     * @param command is the raw string input.
     * @return a Task object representation of the command.
     * @throws DukeException is thrown if the command is incompatible.
     */
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

    /**
     * Returns an integer representing the index to be marked in a TaskList object.
     *
     * @param command is the raw string input.
     * @return an index.
     */
    public static Integer indexToMark(String command) {
        return Integer.parseInt(command.substring(5));
    }

    /**
     * Returns an integer representing the index to be unmarked in a TaskList object.
     *
     * @param command is the raw string input.
     * @return an index.
     */
    public static Integer indexToUnmark(String command) {
        return Integer.parseInt(command.substring(7));
    }

    /**
     * Returns an integer representing the index to be deleted in a TaskList object.
     *
     * @param command is the raw string input.
     * @return an index.
     */
    public static Integer indexToDelete(String command) {
        return Integer.parseInt(command.substring(7));
    }
}



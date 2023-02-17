package duke.parse;
import static duke.task.Task.convertDateTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;



/**
 *      File name: duke.parse.Parser.java
 *      @author: Jerome Neo
 *      Description: duke.parse.Parser class deals with making sense of the user command.
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
    private static final String FIND_PATTERN = "find\\s+\\s*(.*)";

    public Parser() {
    }

    /**
     * Returns a boolean that checks if the command matches the aforementioned command formats.
     *
     * @param command is the raw string input.
     * @return true if the command is recognised, else false.
     */
    public static boolean isValidCommand(String command) {
        String[] commandsCollection = {
            BYE_PATTERN, LIST_PATTERN, MARK_PATTERN, UNMARK_PATTERN, DELETE_PATTERN,
            TODO_PATTERN, DEADLINE_PATTERN, EVENT_PATTERN, FIND_PATTERN
        };
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
        if (!Parser.isTaskCommand(command)) {
            throw new DukeException("OOPS!!! You cannot convert a non-task command into a task.");
        }
        if (command.matches(TODO_PATTERN)) {
            String taskDescription = command.substring(5);
            if (taskDescription.replaceAll("\\s+", "").isBlank()) {
                throw new DukeException("Empty description detected.");
            }
            task = new Todo(taskDescription);
        } else if (command.matches(DEADLINE_PATTERN)) {
            String description = command.substring(9);
            String[] temp = description.split("\\s*/by\\s*");
            String taskDescription = temp[0];
            String by = temp[1];
            if (taskDescription.replaceAll("\\s+", "").isBlank()) {
                throw new DukeException("Empty description detected.");
            }
            task = new Deadline(taskDescription, by);
        } else { // (command.matches(EVENT_PATTERN)) {
            String description = command.substring(6);
            String[] temp = description.split("\\s*/from\\s*");
            String taskDescription = temp[0];
            String[] time = temp[1].split("\\s*/to\\s*");
            String from = time[0];
            String to = time[1];
            if (convertDateTime(from).isAfter(convertDateTime(to))) {
                throw new DukeException("OOPS!!! Wrong date time ordering");
            }
            if (taskDescription.replaceAll("\\s+", "").isBlank()) {
                throw new DukeException("Empty description detected.");
            }
            task = new Event(taskDescription, from, to);
        }
        return task;
    }

    /**
     * Returns an integer representing the index to be marked in a duke.task.TaskList object.
     *
     * @param command is the raw string input.
     * @return an index.
     */
    public static Integer indexToMark(String command) {
        return Integer.parseInt(command.substring(5));
    }

    /**
     * Returns an integer representing the index to be unmarked in a duke.task.TaskList object.
     *
     * @param command is the raw string input.
     * @return an index.
     */
    public static Integer indexToUnmark(String command) {
        return Integer.parseInt(command.substring(7));
    }

    /**
     * Returns an integer representing the index to be deleted in a duke.task.TaskList object.
     *
     * @param command is the raw string input.
     * @return an index.
     */
    public static Integer indexToDelete(String command) {
        return Integer.parseInt(command.substring(7));
    }

    /**
     * Returns a string. Extracts the description of the find command.
     *
     * @param command is equal to find.
     * @return the description only.
     */
    public static String commandToDescription(String command) {
        String[] parts = command.split("find\\s*");
        return parts[1];
    }
}



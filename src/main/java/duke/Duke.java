package duke;

import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.function.Predicate;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidArgumentException;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeSaveLoadException;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;
import duke.tasks.TaskTodo;

/**
 * A simple task list program.
 */
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private String initialMsg = "Hello from\n" + LOGO;
    private Storage storage = new Storage("_duke_data.txt");
    private TaskList tasks;
    private Parser currentCommand;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        // Attempt to load task list from save file.
        try {
            this.tasks = this.storage.load();
        } catch (DukeSaveLoadException e) {
            initialMsg += "\n" + e.getDukeMessage();
            this.tasks = new TaskList();
        }
    }

    /**
     * Handles the adding of todo-type tasks by the "todo" command, and returns the
     * Duke's response.
     * 
     * @return Duke's response.
     * @throws DukeInvalidArgumentException If no description given.
     */
    private String addTodo() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("The description of a todo cannot be empty.");
        }

        String description = this.currentCommand.body;
        Task task = new TaskTodo(description);
        this.tasks.add(task);
        return "Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + this.tasks.getStatus();
    }

    /**
     * Handles the adding of deadline-type tasks by the "deadline" command, and
     * returns the Duke's response.
     * 
     * @return Duke's response.
     * @throws DukeInvalidArgumentException If no or invalid description/by-param
     *         are given.
     */
    private String addDeadline() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("The description of a deadline cannot be empty.");
        }
        if (!this.currentCommand.namedParameters.containsKey("by")) {
            throw new DukeInvalidArgumentException("The \"/by\" parameter of a deadline is missing.");
        }
        if (this.currentCommand.namedParameters.get("by").isEmpty()) {
            throw new DukeInvalidArgumentException("The \"/by\" parameter of a deadline cannot be empty.");
        }

        try {
            String description = this.currentCommand.body;
            Task task = new TaskDeadline(description, this.currentCommand.namedParameters.get("by"));
            this.tasks.add(task);
            return "Got it. I've added this task:\n"
                    + "  " + task.toString() + "\n"
                    + this.tasks.getStatus();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                    "The \"/by\" value must be in the form \"yyyy-mm-dd\" (eg. 2019-10-15).");
        }
    }

    /**
     * Handles the adding of event-type tasks by the "event" command, and returns
     * the Duke's response.
     * 
     * @throws DukeInvalidArgumentException If no or invalid
     *         description/from-param/to-param are given.
     */
    private String addEvent() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("The description of an event cannot be empty.");
        }
        if (!this.currentCommand.namedParameters.containsKey("from")) {
            throw new DukeInvalidArgumentException("The \"/from\" parameter of an event is missing.");
        }
        if (this.currentCommand.namedParameters.get("from").isEmpty()) {
            throw new DukeInvalidArgumentException("The \"/from\" parameter of an event cannot be empty.");
        }
        if (!this.currentCommand.namedParameters.containsKey("to")) {
            throw new DukeInvalidArgumentException("The \"/to\" parameter of an event is missing.");
        }
        if (this.currentCommand.namedParameters.get("to").isEmpty()) {
            throw new DukeInvalidArgumentException("The \"/to\" parameter of an event cannot be empty.");
        }

        try {
            String description = this.currentCommand.body;
            Task task = new TaskEvent(
                    description,
                    this.currentCommand.namedParameters.get("from"),
                    this.currentCommand.namedParameters.get("to"));
            this.tasks.add(task);
            return "Got it. I've added this task:\n"
                    + "  " + task.toString() + "\n"
                    + this.tasks.getStatus();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                    "The \"/from\" and \"/to\" values must be in the form \"yyyy-mm-dd\" (eg. 2019-10-15).");
        }
    }

    /**
     * Handles the marking of tasks as done, by the "mark" command, and returns the
     * Duke's response.
     * 
     * @return Duke's response.
     * @throws DukeInvalidArgumentException If no or invalid task-index is given.
     */
    private String mark() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("No task index given.");
        }

        Predicate<String> isNumeric = str -> str.matches("^-?\\d+$");
        int taskIndex = Optional.of(this.currentCommand.body)
                .filter(isNumeric)
                .map(body -> Integer.parseInt(body) - 1)
                .filter(i -> i >= 0)
                .orElseThrow(() -> new DukeInvalidArgumentException(
                        "Invalid task index. Index needs to be a positive integer."));
        Task task = Optional.of(taskIndex)
                .filter(index -> index < this.tasks.size())
                .map(index -> this.tasks.get(index))
                .orElseThrow(() -> new DukeInvalidArgumentException(
                        "Task index is beyond the range of the task list."));
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + "  " + task.toString();
    }

    /**
     * Handles the marking of tasks as not done, by the "unmark" command, and
     * returns the Duke's response.
     * 
     * @return Duke's response.
     * @throws DukeInvalidArgumentException If no or invalid task-index is given.
     */
    private String unmark() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("No task index given.");
        }

        Predicate<String> isNumeric = str -> str.matches("^-?\\d+$");
        int taskIndex = Optional.of(this.currentCommand.body)
                .filter(isNumeric)
                .map(body -> Integer.parseInt(body) - 1)
                .filter(i -> i >= 0)
                .orElseThrow(() -> new DukeInvalidArgumentException(
                        "Invalid task index. Index needs to be a positive integer."));
        Task task = Optional.of(taskIndex)
                .filter(index -> index < this.tasks.size())
                .map(index -> this.tasks.get(index))
                .orElseThrow(() -> new DukeInvalidArgumentException(
                        "Task index is beyond the range of the task list."));
        task.markAsNotDone();
        return "OK, I've marked this task as not done yet:\n" + "  " + task.toString();
    }

    /**
     * Handles the deleting of tasks by the "delete" command, and returns the Duke's
     * response.
     * 
     * @return Duke's response.
     * @throws DukeInvalidArgumentException If no or invalid task-index is given.
     */
    private String delete() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("No task index given.");
        }

        Predicate<String> isNumeric = str -> str.matches("^-?\\d+$");
        int taskIndex = Optional.of(this.currentCommand.body)
                .filter(isNumeric)
                .map(body -> Integer.parseInt(body) - 1)
                .filter(i -> i >= 0)
                .orElseThrow(() -> new DukeInvalidArgumentException(
                        "Invalid task index. Index needs to be a positive integer."));
        Task task = Optional.of(taskIndex)
                .filter(index -> index < this.tasks.size())
                .map(index -> this.tasks.get(index))
                .orElseThrow(() -> new DukeInvalidArgumentException(
                        "Task index is beyond the range of the task list."));
        this.tasks.remove(taskIndex);
        return "Noted. I've removed this task:\n" + "  " + task.toString();
    }

    /**
     * Handles the listing of tasks by the "list" command, and returns the Duke's
     * response.
     * 
     * @return Duke's response.
     */
    private String list() {
        String header = "Here are the tasks in your list:\n";
        return header + this.tasks.toString();
    }

    /**
     * Handles the finding of tasks using keywords, by the "find" command, and
     * returns the Duke's response.
     * 
     * @return Duke's response.
     */
    private String find() {
        String keyword = this.currentCommand.body;
        Task[] matchingTasks = this.tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .toArray(Task[]::new);
        TaskList matchingTaskList = new TaskList(matchingTasks);

        String header = "Here are the matching tasks in your list:\n";
        return header + matchingTaskList.toString();
    }

    /**
     * Gets the Duke's initialisation response.
     * 
     * @return Initialisation response.
     */
    public String getInitialResponse() {
        return initialMsg;
    }

    /**
     * Gets Duke's response to a user input. Returns 'null' if app is to be closed.
     * 
     * @param input User input.
     * @return Duke's response, or 'null' if the app is to be closed.
     */
    public String getResponse(String input) {
        if (input.isEmpty()) {
            return "";
        }

        this.currentCommand = new Parser(input);
        String response;

        try {
            switch (this.currentCommand.baseCommand) {
            case "todo":
                response = this.addTodo();
                break;
            case "deadline":
                response = this.addDeadline();
                break;
            case "event":
                response = this.addEvent();
                break;
            case "mark":
                response = this.mark();
                break;
            case "unmark":
                response = this.unmark();
                break;
            case "delete":
                response = this.delete();
                break;
            case "list":
                response = this.list();
                break;
            case "find":
                response = this.find();
                break;
            case "q":
            case "quit":
            case "exit":
            case "bye":
                return null;
            default:
                throw new DukeInvalidCommandException();
            }

            this.storage.save(this.tasks);
            return response;
        } catch (DukeException e) {
            return e.getDukeMessage();
        }
    }
}

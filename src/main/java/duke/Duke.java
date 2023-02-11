package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.repository.Storage;
import duke.repository.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Contains Logic of Duke Application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;


    /**
     * Constructor of Duke class.
     *
     * @param filePath file path to where persistent storage reside.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException dukeException) {
            tasks = new TaskList();
        }
    }


    /**
     * Starts Duke application.
     * Sets up persistent storage and user interface. Begins to receive and process input form user.
     * Terminates based on user input.
     */
    public String getResponse(String userInput) {
        if (userInput.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }

        String[] splitInput = userInput.split(" ", 2);
        try {
            Command inputCommand = Parser.validateCommand(splitInput[0]);
            String outputMsg = "";
            switch (inputCommand) {
            case LIST:
                outputMsg = listTask();
                break;
            case MARK:
                outputMsg = markTask(splitInput);
                break;
            case UNMARK:
                outputMsg = unmarkTask(splitInput);
                break;
            case DELETE:
                outputMsg = deleteTask(splitInput);
                break;
            case TODO:
                outputMsg = addTodo(splitInput);
                break;
            case DEADLINE:
                outputMsg = addDeadline(splitInput);
                break;
            case EVENT:
                outputMsg = addEvent(splitInput);
                break;
            case FIND:
                outputMsg = findTask(splitInput);
                break;
            default:
                throw new DukeException("...Hmm... I'm not sure what that means...");
            } //end of switch-case
            return outputMsg;
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        }
    }


    /**
     * Creates and stores user Todo based on user input.
     * Saves to persistent storage and in <code>TaskList</code>.
     *
     * @param input User input separated into {command, description}.
     * @throws DukeException If user input is invalid, e.g. empty description.
     */
    private String addTodo(String[] input) throws DukeException {
        Todo tempTodo = Parser.parseTodo(input);
        this.storage.saveEntry(tempTodo);
        this.tasks.addTask(tempTodo);
        return generateAddTaskMessage(tempTodo);
    }


    private String addDeadline(String[] input) throws DukeException {
        Deadline tempDeadline = Parser.parseDeadline(input);

        storage.saveEntry(tempDeadline);
        tasks.addTask(tempDeadline);
        return generateAddTaskMessage(tempDeadline);

    }

    private String addEvent(String[] input) throws DukeException {
        Event tempEvent = Parser.parseEvent(input);
        storage.saveEntry(tempEvent);
        tasks.addTask(tempEvent);
        return generateAddTaskMessage(tempEvent);
    }

    private String generateAddTaskMessage(Task task) {
        String message = "Got it. I've added this task:\n " + task.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        return message;
    }

    private String generateDeleteTaskMessage(Task task) {
        String message = "Noted. I've removed this task:\n " + task.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        return message;
    }

    private String listTask() {
        String message = "Here are the tasks in your list:";
        message += tasks.getTaskList();
        return message;
    }

    private String findTask(String[] input) throws DukeException {
        String searchString = Parser.parseSearch(input);
        String message = "Here are the matching tasks in your list:";
        message += tasks.getMatchingTasks(searchString);
        return message;
    }

    private String markTask(String[] input) throws DukeException {

        int taskNum = Parser.parseMarkTask(input);
        if (taskNum > tasks.numTasks() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        assert taskNum <= tasks.numTasks() : "Referenced task number should be <= number existing task";
        storage.markEntry(taskNum - 1, true);

        Task oneTask = tasks.getTask(taskNum - 1);
        tasks.markTask(taskNum - 1);

        String message = "Nice! I've marked this task as done:\n " + oneTask.toString();
        return message;
    }

    private String unmarkTask(String[] input) throws DukeException {
        int taskNum = Parser.parseUnmarkTask(input);

        if (taskNum > tasks.numTasks() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        assert taskNum <= tasks.numTasks() : "Referenced task number should be <= number existing task";
        storage.markEntry(taskNum - 1, false);

        Task oneTask = tasks.getTask(taskNum - 1);
        tasks.unmarkTask(taskNum - 1);
        String message = "OK! I've marked this task as not done yet:\n " + oneTask.toString();
        return message;
    }

    private String deleteTask(String[] input) throws DukeException {
        int taskNum = Parser.parseDeleteTask(input);
        if (taskNum > tasks.numTasks() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        assert taskNum <= tasks.numTasks() : "Referenced task number should be <= number existing task";
        storage.deleteEntry(taskNum - 1);

        Task delTask = tasks.getTask(taskNum - 1);
        tasks.deleteTask(taskNum - 1);
        return generateDeleteTaskMessage(delTask);
    }


}

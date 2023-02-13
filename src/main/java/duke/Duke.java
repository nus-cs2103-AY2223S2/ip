package duke;

import java.util.ArrayList;

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
        ArrayList<Integer> taskIds = Parser.parseMarkTask(input);
        if (!validateIdsRange(taskIds)) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }

        storage.markEntries(taskIds, true);
        ArrayList<Task> selectedTasks = tasks.getMultipleTasks(taskIds);
        return generateMarkMessage(selectedTasks, true);
    }

    private String unmarkTask(String[] input) throws DukeException {
        ArrayList<Integer> taskIds = Parser.parseUnmarkTask(input);
        if (!validateIdsRange(taskIds)) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }

        storage.markEntries(taskIds, false);
        ArrayList<Task> selectedTasks = tasks.getMultipleTasks(taskIds);
        return generateMarkMessage(selectedTasks, false);
    }


    private String deleteTask(String[] input) throws DukeException {
        ArrayList<Integer> taskIds = Parser.parseDeleteTask(input);
        if (!validateIdsRange(taskIds)) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }

        storage.deleteEntries(taskIds);
        ArrayList<Task> selectedTasks = tasks.getMultipleTasks(taskIds);
        tasks.deleteTasks(taskIds);
        return generateDeleteTaskMessage(selectedTasks);
    }

    private String generateAddTaskMessage(Task task) {
        String message = "Got it. I've added this task:\n" + task.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        return message;
    }

    private String generateDeleteTaskMessage(ArrayList<Task> selectedTasks) {
        String message = "Noted. I've removed these tasks:\n";
        for (int i = 0; i < selectedTasks.size(); i++) {
            Task oneTask = selectedTasks.get(i);
            message += oneTask.toString() + "\n";
        }
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        return message;
    }

    private String generateMarkMessage(ArrayList<Task> selectedTasks, boolean isMark) {
        String unmarkMsg = "OK! I've marked these tasks as not done yet:\n";
        String markMsg = "Nice! I've marked these tasks as done:\n";
        String message = isMark ? markMsg : unmarkMsg;
        for (int i = 0; i < selectedTasks.size(); i++) {
            Task oneTask = selectedTasks.get(i);
            if (isMark) {
                oneTask.markTask();
            } else {
                oneTask.unmarkTask();
            }
            message += oneTask.toString() + "\n";
        }
        return message;
    }

    private boolean validateIdsRange(ArrayList<Integer> taskIds) {
        for (int i = 0; i < taskIds.size(); i++) {
            int taskNum = taskIds.get(i);
            if (taskNum > tasks.numTasks() - 1 || taskNum <= -1) {
                return false;
            }
            assert taskNum <= tasks.numTasks() : "Referenced task number should be <= number existing task";
        }
        return true;
    }

}

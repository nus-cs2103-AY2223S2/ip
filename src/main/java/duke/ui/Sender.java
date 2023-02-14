package duke.ui;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.ToDo;

/**
 * Handles interactions with the user.
 */
public class Sender {

    /** Handles the storing of tasks in the hard drive. */
    private Storage storage;

    /** Handles all the tasks in the current session. */
    private TaskList tasks;

    private String DUKE_RESPONSE = "Duke's response: \n";

    /**
     * Duke's help message
     */
    private static String HELP_MESSAGE = "The available commands are: \n" +
            "1) list\n" +
            "2) bye\n" +
            "3) todo ________\n" +
            "4) deadline ______ /by <YYYY-MM-DD> \n" +
            "5) event ________ /from _______  /to _________\n" +
            "6) doafter ______ /after _______\n" +
            "7) unmark ____\n" +
            "8) mark ______ \n" +
            "9) help\n" +
            "10) find ______ \n";

    public Sender(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public String sendGoodByeMessage() {
        return "Goodbye! :)\n";
    }

    public String sendInvalidCommandMessage() {
        return "Oops! I don't know what this means. For a list of valid " +
                "commands to use, " +
                "type in 'help'.";
    }

    /**
     * Displays a help message to the user.
     */
    public String sendHelpMessage() {
        return HELP_MESSAGE;
    }

    public String listAllTasks()  {
        String taskString = "";
        for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
            Task task = tasks.getTask(i);
            taskString += String.format("%s): %s\n", i, task);
        }
        return DUKE_RESPONSE + "These are the current tasks: \n" + taskString;
    }

    public String listMatchingTasks(String searchWord) {
        ArrayList<Task> matchingTasks = tasks.findTask(searchWord);
        String taskString = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            taskString += task + "\n";
        }
        return DUKE_RESPONSE + "These are the matching tasks: \n" + taskString;
    }



    public String markTask(int taskNumberToMark) throws DukeException {
        try {
            Task taskToMark = tasks.getTask(taskNumberToMark);
            storage.changeTaskStatus(taskToMark.getStorageLine());
            tasks.mark(taskNumberToMark);
            return "I have marked this task as done! \n" + taskToMark;
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem marking this task. Please try again.");
        }
    }

    public String unmarkTask(int taskNumberToUnmark) throws DukeException {
        try {
        Task taskToUnmark = tasks.getTask(taskNumberToUnmark);
        storage.changeTaskStatus(taskToUnmark.getStorageLine());
        tasks.unmark(taskNumberToUnmark);
        return "I have marked this task as undone! \n" + taskToUnmark;
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem unmarking this task. Please try again.");
        }
    }

    public String addToDo(String taskDescription) throws DukeException {
        try {
        ToDo toDo = new ToDo("todo", taskDescription, false);
        tasks.addTask(toDo);
        storage.addTask(toDo.getStorageLine());
        return "I have added this new task:\n" + toDo
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this todo. Please try again.");
        }
    }

    public String addDeadline(String taskDescription) throws DukeException {
        try {
        ToDo deadline = new ToDo("deadline", taskDescription, false);
        tasks.addTask(deadline);
        storage.addTask(deadline.getStorageLine());
        return "I have added this new task:\n" + deadline
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this deadline. Please try again.");
        }
    }

    public String addEvent(String taskDescription) throws DukeException {
        try {
        ToDo toDo = new ToDo("event", taskDescription, false);
        tasks.addTask(toDo);
        storage.addTask(toDo.getStorageLine());
        return "I have added this new task:\n" + toDo
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this event. Please try again.");
        }
    }

    public String addDoAfter(String taskDescription) throws DukeException {
        try {
        ToDo toDo = new ToDo("doafter", taskDescription, false);
        tasks.addTask(toDo);
        storage.addTask(toDo.getStorageLine());
        return "I have added this new task:\n" + toDo
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this doafter. Please try again.");
        }
    }

    public String deleteTask(int taskNumberToDelete) throws DukeException {
        try {
        Task taskToDelete = tasks.getTask(taskNumberToDelete);
        tasks.deleteTask(taskNumberToDelete);
        storage.deleteTask(taskToDelete.getStorageLine());
        return "We have removed this task: " + taskToDelete + "\nYou now have "
                + tasks.getTaskCount() + " tasks remaining";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem deleting this task. Please try again.");
        }
    }
}

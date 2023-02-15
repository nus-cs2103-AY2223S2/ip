package duke.ui;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.ToDo;
import duke.tasks.DeadLine;
import duke.tasks.Event;
import duke.tasks.DoAfter;


/**
 * Handles interactions with the user.
 */
public class Sender {

    /** Handles the storing of tasks in the hard drive. */
    private Storage storage;

    /** Handles all the tasks in the current session. */
    private TaskList tasks;

    /** A string that is always used when Duke responds to the user. */
    private String DUKE_RESPONSE = "Duke's response: \n";

    /**
     * Duke's help message
     */
    private static String HELP_MESSAGE = "The available commands are: \n" +
            "1) list\n" +
            "2) bye\n" +
            "3) todo _____\n" +
            "4) deadline _____ /by _YYYY-MM-DD_\n" +
            "5) event _____ /from _____ /to _____\n" +
            "6) doafter _____ /after _____ \n" +
            "7) unmark _____\n" +
            "8) mark _____ \n" +
            "9) find _____\n" +
            "10 delete _____\n" +
            "11) help\n";

    /**
     * Constructs a new Sender instance.
     * @param storage
     * @param tasks
     */
    public Sender(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Sends a goodbye message to the user.
     * @return a goodbye message
     */
    public String sendGoodByeMessage() {
        return "Goodbye! :)\n";
    }

    /**
     * Sends a message to the user informing them that their command was invalid.
     * @return an invalid command message
     */
    public String sendInvalidCommandMessage() {
        return "Oops! I don't know what this means. For a list of valid " +
                "commands to use, " +
                "type in 'help'.";
    }

    /**
     * Sends a help message to the user.
     * @return a help message
     */
    public String sendHelpMessage() {
        return HELP_MESSAGE;
    }

    /**
     * Lists all the current tasks to the user.
     * @return a description of all the current tasks
     */
    public String listAllTasks()  {
        String taskString = "";
        for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
            Task task = tasks.getTask(i);
            taskString += String.format("%s): %s\n", i, task);
        }
        return DUKE_RESPONSE + "These are the current tasks: \n" + taskString;
    }

    /**
     * Lists all the matching tasks based on a keyword provided by the user.
     * @param searchWord a keyword provided by the user
     * @return all the matching tasks
     */
    public String listMatchingTasks(String searchWord) {
        ArrayList<Task> matchingTasks = tasks.findTask(searchWord);
        String taskString = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            taskString += task + "\n";
        }
        return DUKE_RESPONSE + "These are the matching tasks: \n" + taskString;
    }

    /**
     * Marks a task.
     * @param taskNumberToMark the number of the task to mark
     * @return a notification that the task has been marked
     * @throws DukeException if there was an error marking the task
     */
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

    /**
     * Un-marks a task.
     * @param taskNumberToUnmark the number of the task to unmark
     * @return a notification that the task has been unmarked
     * @throws DukeException if there is an error unmarking the task
     */
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

    /**
     * Adds a new to do task.
     * @param taskDescription description of the task
     * @return a notification that a new to do task was added
     * @throws DukeException if there was an error adding the to do task
     */
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

    /**
     * Adds a new deadline
     * @param taskDescription description of the task
     * @return a notification that a new deadline task was added
     * @throws DukeException if there was an error adding the deadline task
     */
    public String addDeadLine(String taskDescription) throws DukeException {
        try {
        DeadLine deadline = new DeadLine("deadline", taskDescription, false);
        tasks.addTask(deadline);
        storage.addTask(deadline.getStorageLine());
        return "I have added this new task:\n" + deadline
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this deadline. Please try again.");
        }
    }

    /**
     * Adds a new event.
     * @param taskDescription description of the task
     * @return a notification that a new event was added
     * @throws DukeException if there was an error adding the event
     */
    public String addEvent(String taskDescription) throws DukeException {
        try {
        Event event = new Event("event", taskDescription, false);
        tasks.addTask(event);
        storage.addTask(event.getStorageLine());
        return "I have added this new task:\n" + event
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this event. Please try again.");
        }
    }

    /**
     * Adds a new do after task.
     * @param taskDescription description of the task
     * @return a notification that a new do after task was added
     * @throws DukeException if there was an error adding the do after task
     */
    public String addDoAfter(String taskDescription) throws DukeException {
        try {
        DoAfter doAfter = new DoAfter("doafter", taskDescription, false);
        tasks.addTask(doAfter);
        storage.addTask(doAfter.getStorageLine());
        return "I have added this new task:\n" + doAfter
                + "\nYou now currently have "
                + tasks.getTaskCount() + " tasks.";
        } catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this doafter. Please try again.");
        }
    }

    /**
     * Deletes a task.
     * @param taskNumberToDelete the number of the task to delete
     * @return a notification informing that the task was deleted successfully
     * @throws DukeException if there was an error deleting the task
     */
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

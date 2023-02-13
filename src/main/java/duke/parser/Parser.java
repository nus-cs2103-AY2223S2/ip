package duke.parser;

import com.sun.java.accessibility.util.GUIInitializedListener;
import duke.tasks.Task;
import duke.tasks.DeadLine;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

/**
 * Parses user input and returns a String that can then be understood by Sender.
 */
public class Parser {

    private Ui ui;

    private Storage storage;

    private TaskList tasks;

    public Parser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Main method for parsing user input.
     *
     * @param userInput The user input.
     */
    public String parse(String userInput) throws DukeException {
        String[] inputSplitByWords = userInput.split(" ", 2);
        String firstWord = inputSplitByWords[0];
        try {
            switch (firstWord) {
                case "bye":
                    return ui.respond("Goodbye! Have a nice day ahead.\n");
                case "list":
                    return ui.listTasks(tasks);
                case "help":
                    return ui.showHelpMessage();
                case "find":
                    //Body message should be a keyword to search for a task
                    String searchWord = userInput.split(" ", 2)[1];
                    return ui.listMatchingTasks(tasks.findTask(searchWord));
                case "mark":
                    //second item in the String array should be an integer indicating which Task to mark or unmark.
                    int taskNumberToMark = Integer.parseInt(inputSplitByWords[1]);
                    Task taskToMark = tasks.getTask(taskNumberToMark);
                    storage.changeTaskStatus(taskToMark.getStorageLine());
                    tasks.mark(taskNumberToMark);
                    return ui.respond("I have marked this task as done! \n" + taskToMark.provideDetails());
                case "unmark":
                    //second item in the String array should be an integer indicating which Task to mark or unmark.
                    int taskNumberToUnmark = Integer.parseInt(inputSplitByWords[1]);
                    Task taskToUnmark = tasks.getTask(taskNumberToUnmark);
                    storage.changeTaskStatus(taskToUnmark.getStorageLine());
                    tasks.unmark(taskNumberToUnmark);
                    return ui.respond("I have marked this task as undone! \n" + taskToUnmark.provideDetails());
                case "todo":
                    //Rest of message describes the Task.
                    String bodyToDo = userInput.split(" ", 2)[1];
                    ToDo toDo = new ToDo("todo", bodyToDo, false);
                    tasks.addTask(toDo);
                    storage.addTask(toDo.getStorageLine());
                    return ui.respond("I have added this new task:\n" + toDo.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                case "deadline":
                    //Rest of message describes the Task.
                    String bodyDeadline = userInput.split(" ", 2)[1];
                    DeadLine deadline = new DeadLine("deadline", bodyDeadline, false);
                    tasks.addTask(deadline);
                    storage.addTask(deadline.getStorageLine());
                    return ui.respond("I have added this new task:\n" + deadline.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                case "event":
                    //Rest of message describes the Task.
                    String bodyEvent = userInput.split(" ", 2)[1];
                    Event event = new Event("event", bodyEvent, false);
                    tasks.addTask(event);
                    storage.addTask(event.getStorageLine());
                    return ui.respond("I have added this new task:\n" + event.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                case "delete":
                    //second word should be an integer
                    int taskNumberToDelete = Integer.parseInt(inputSplitByWords[1]);
                    Task taskToDelete = tasks.getTask(taskNumberToDelete);
                    tasks.deleteTask(taskNumberToDelete);
                    storage.deleteTask(taskToDelete.getStorageLine());
                    return ui.respond("We have removed this task: " + taskToDelete.provideDetails() + "\nYou now have "
                            + tasks.getTaskCount() + " tasks remaining");
                default:
                    return ui.respond("Oops! I don't know what this means. For a list of valid commands to use, " +
                            "type in 'help'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DukeException("There seems to be something wrong with your command." +
                    " For a list of valid commands to use, " +
                    "type in 'help'.");
        }
    }

}


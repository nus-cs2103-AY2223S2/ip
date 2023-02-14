package duke.parser;

import duke.tasks.*;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.ui.Sender;

/**
 * Parses user input and returns a String that can then be understood by Sender.
 */
public class Parser {

    private Sender sender;

    private Storage storage;

    private TaskList tasks;

    public Parser(Sender sender, Storage storage, TaskList tasks) {
        this.sender = sender;
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
                    return sender.respond("Goodbye! Have a nice day ahead.\n");
                case "list":
                    return sender.listTasks(tasks);
                case "help":
                    return sender.showHelpMessage();
                case "find":
                    //Body message should be a keyword to search for a task
                    String searchWord = userInput.split(" ", 2)[1];
                    return sender.listMatchingTasks(tasks.findTask(searchWord));
                case "mark":
                    //second item in the String array should be an integer indicating which Task to mark or unmark.
                    int taskNumberToMark = Integer.parseInt(inputSplitByWords[1]);
                    Task taskToMark = tasks.getTask(taskNumberToMark);
                    storage.changeTaskStatus(taskToMark.getStorageLine());
                    tasks.mark(taskNumberToMark);
                    return sender.respond("I have marked this task as done! \n" + taskToMark.provideDetails());
                case "unmark":
                    //second item in the String array should be an integer indicating which Task to mark or unmark.
                    int taskNumberToUnmark = Integer.parseInt(inputSplitByWords[1]);
                    Task taskToUnmark = tasks.getTask(taskNumberToUnmark);
                    storage.changeTaskStatus(taskToUnmark.getStorageLine());
                    tasks.unmark(taskNumberToUnmark);
                    return sender.respond("I have marked this task as undone! \n" + taskToUnmark.provideDetails());
                case "todo":
                    //Rest of message describes the Task.
                    String bodyToDo = userInput.split(" ", 2)[1];
                    ToDo toDo = new ToDo("todo", bodyToDo, false);
                    tasks.addTask(toDo);
                    storage.addTask(toDo.getStorageLine());
                    return sender.respond("I have added this new task:\n" + toDo.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                case "deadline":
                    //Rest of message describes the Task.
                    String bodyDeadline = userInput.split(" ", 2)[1];
                    DeadLine deadline = new DeadLine("deadline", bodyDeadline, false);
                    tasks.addTask(deadline);
                    storage.addTask(deadline.getStorageLine());
                    return sender.respond("I have added this new task:\n" + deadline.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                case "event":
                    //Rest of message describes the Task.
                    String bodyEvent = userInput.split(" ", 2)[1];
                    Event event = new Event("event", bodyEvent, false);
                    tasks.addTask(event);
                    storage.addTask(event.getStorageLine());
                    return sender.respond("I have added this new task:\n" + event.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                case "delete":
                    //second word should be an integer
                    int taskNumberToDelete = Integer.parseInt(inputSplitByWords[1]);
                    Task taskToDelete = tasks.getTask(taskNumberToDelete);
                    tasks.deleteTask(taskNumberToDelete);
                    storage.deleteTask(taskToDelete.getStorageLine());
                    return sender.respond("We have removed this task: " + taskToDelete.provideDetails() + "\nYou now have "
                            + tasks.getTaskCount() + " tasks remaining");
                case "doafter":
                    //Rest of message describes the Task.
                    String bodyDoAfter = userInput.split(" ", 2)[1];
                    DoAfter doAfter = new DoAfter("doafter", bodyDoAfter, false);
                    tasks.addTask(doAfter);
                    storage.addTask(doAfter.getStorageLine());
                    return sender.respond("I have added this new task:\n" + doAfter.provideDetails()
                            + "\nYou now currently have "
                            + tasks.getTaskCount() + " tasks.");
                default:
                    return sender.respond("Oops! I don't know what this means. For a list of valid commands to use, " +
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


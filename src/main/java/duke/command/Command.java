package duke.command;

import duke.exception.DukeException;
import duke.task.*;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Executes a command based on parsed inputs.
 */
public class Command {
    private String commandName;
    private String taskName;
    private String by;
    private String from;
    private String to;
    private int index;
    private boolean isExit = false;


    private enum CommandName {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
    }

    /**
     * Constructor for commands 'bye' and 'list'.
     *
     * @param commandName The name of the command.
     */
    public Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Constructor for commands "mark", "unmark" and "delete".
     *
     * @param commandName The name of the command.
     * @param index The index of the task in taskList to be executed on.
     */
    public Command(String commandName, int index) {
        this.commandName = commandName;
        this.index = index;
    }

    /**
     * Constructor for command "todo" and "find".
     *
     * @param commandName The name of the command.
     * @param taskName The name of the todo task.
     */
    public Command(String commandName, String taskName) {
        this.commandName = commandName;
        this.taskName = taskName;
    }

    /**
     * Constructor for command "deadline".
     *
     * @param commandName The name of the command.
     * @param taskName The name of the deadline task.
     * @param by The date of the deadline, must be in yyyy-mm-dd format.
     */
    public Command(String commandName, String taskName, String by) {
        this.commandName = commandName;
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * Constructor for command "deadline".
     *
     * @param commandName The name of the command.
     * @param taskName The name of the event task.
     * @param from The event start date.
     * @param to The event end date.
     */
    public Command(String commandName, String taskName, String from, String to) {
        this.commandName = commandName;
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the given command.
     * Throws exception if the argument is not recognized or in a wrong format.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        CommandName cn = CommandName.valueOf(this.commandName.toUpperCase());
        Task task;

        switch(cn) {
        case BYE:
            ui.farewellMessage();
            this.isExit = true;
            break;

        case LIST:
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            break;

        case MARK:
            if (index + 1 == 0) {
                throw new DukeException("cannot mark a number not in the list!");
            }
            task = tasks.get(index);
            task.mark();
            storage.saveTasks(tasks);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
            break;

        case UNMARK:
            if (index+1 == 0) {
                throw new DukeException("cannot unmark a number not in the list!");
            }
            task = tasks.get(index);
            task.unmark();
            storage.saveTasks(tasks);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
            break;

        case DELETE:
            task = tasks.get(index);
            tasks.delete(index);
            storage.saveTasks(tasks);
            System.out.println("Noted. I've removed this task: \n" + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;

        case TODO:
            Todo todo = new Todo(taskName);
            tasks.add(todo);
            storage.saveTasks(tasks);
            System.out.println("Got it. I've added this task: \n" + todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;

        case FIND:
            int counter = 0;
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task findTask = tasks.get(i);
                if (findTask.contains(taskName)) {
                    counter++;
                    System.out.print(counter + ". " + findTask + "\n");
                }
            }
            break;

        case DEADLINE:
            Deadline deadline = new Deadline(taskName, by);
            tasks.add(deadline);
            storage.saveTasks(tasks);
            System.out.println("Got it. I've added this task: \n" + deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;

        case EVENT:
            Event event = new Event(taskName, from, to);
            tasks.add(event);
            storage.saveTasks(tasks);
            System.out.println("Got it. I've added this task: \n" + event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;

        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns false by default and true if "bye" command is executed
     *
     * @return false by default and true if "bye" command is executed
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns command name for testing
     *
     * @return String of command name of the command object.
     */
    @Override
    public String toString() {
        return "commandName = " + commandName;
    }
}

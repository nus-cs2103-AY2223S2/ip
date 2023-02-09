package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        CommandName cn = CommandName.valueOf(this.commandName.toUpperCase());
        Task task;

        switch(cn) {
        case BYE:
            this.isExit = true;
            return ui.farewellMessage();

        case LIST:
            StringBuilder listOfTasks = new StringBuilder("Here are your tasks: \n");
            for (int i = 0; i < tasks.getSize(); i++) {
                listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return listOfTasks.toString();

        case MARK:
            if (index + 1 == 0) {
                throw new DukeException("cannot mark a number not in the list!");
            }
            task = tasks.get(index);
            task.mark();
            storage.saveTasks(tasks);
            return "Nice! I've marked this task as done:\n" + task;

        case UNMARK:
            if (index + 1 == 0) {
                throw new DukeException("cannot unmark a number not in the list!");
            }
            task = tasks.get(index);
            task.unmark();
            storage.saveTasks(tasks);
            return "OK, I've marked this task as not done yet:\n" + task;

        case DELETE:
            task = tasks.get(index);
            tasks.delete(index);
            storage.saveTasks(tasks);
            return "Noted. I've removed this task: \n" + task +
                    "\nNow you have " + tasks.getSize() + " tasks in the list.";

        case TODO:
            Todo todo = new Todo(taskName);
            tasks.add(todo);
            storage.saveTasks(tasks);
            return "Got it. I've added this task: \n" + todo +
                    "\nNow you have " + tasks.getSize() + " tasks in the list.";

        case FIND:
            int counter = 0;
            StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                Task findTask = tasks.get(i);
                if (findTask.contains(taskName)) {
                    counter++;
                    matchingTasks.append(counter).append(". ").append(findTask).append("\n");
                }
            }
            return matchingTasks.toString();

        case DEADLINE:
            Deadline deadline = new Deadline(taskName, by);
            tasks.add(deadline);
            storage.saveTasks(tasks);
            return "Got it. I've added this task: \n" + deadline +
                    "\nNow you have " + tasks.getSize() + " tasks in the list.";

        case EVENT:
            Event event = new Event(taskName, from, to);
            tasks.add(event);
            storage.saveTasks(tasks);
            return "Got it. I've added this task: " + event +
                    "\now you have " + tasks.getSize() + " tasks in the list.";

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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

package henz.command;

import java.util.Arrays;

import henz.dukeexception.CommandException;
import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.ui.Ui;

/**
 * Command is a base abstract class for all commands.
 */
public abstract class Command {
    private String request;
    private Commands command;
    private boolean isExit = false;

    /**
     * The Command types supported by Duke.
     */
    public enum Commands {
        LIST, FIND, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE, EXIT, EDIT, DOES_NOT_EXIST
    };

    /**
     * Constructor.
     * @param command the command type
     */
    public Command(Commands command) {
        this.command = command;
    }

    /**
     * Another constructor.
     * @param request the user command
     */
    public Command(String request) {
        this.request = request;
        this.command = getCommand(request);
    }

    /**
     * Executes the creation and adding of task to the task list.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage intance
     * @return string of the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command has the inputs that it needs for the creation of task.
     * @throws CommandException
     */
    public void checkCommandRequirement() throws CommandException {
    }

    /**
     * Retuns a boolean to tell if the command is an exit command.
     * @return a boolean to tell if the command is an exit command
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Toggles isExit.
     */
    public void toggleIsExit() {
        this.isExit = !this.isExit;
    }

    /**
     * Returns the string of the original user command.
     * @return the string of the original user command
     */
    public String getRequest() {
        return this.request;
    }

    /**
     * Unwraps the user's command into a string array that holds information for the
     * command to work with.
     * @return a string array that stores the task description.
     * @throws CommandException
     */
    public String[] unwrap() throws CommandException {
        // 1. Prepare the string by extracting everything after the command.
        String[] values = this.request.split(" ");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, values.length));

        // 2. Checks that non-LIST commands have a description.
        if (this.command != Commands.LIST && description.isEmpty()) {
            throw new CommandException("Description cannot be empty!");
        }

        return new String[] { description };
    }

    /**
     * Returns the command type.
     * @return the command type
     */
    public Commands getCommand() {
        return this.command;
    }

    /**
     * Returns the command type of from the user request
     * @param request the user input
     * @return the command type
     */
    public static Commands getCommand(String request) {
        String cmd = request.split(" ")[0];

        switch (cmd) {
        case "list":
            return Commands.LIST;
        case "find":
            return Commands.FIND;
        case "unmark":
            return Commands.UNMARK;
        case "mark":
            return Commands.MARK;
        case "todo":
            return Commands.TODO;
        case "deadline":
            return Commands.DEADLINE;
        case "event":
            return Commands.EVENT;
        case "delete":
            return Commands.DELETE;
        case "bye":
            return Commands.EXIT;
        case "edit":
            return Commands.EDIT;
        default:
            return Commands.DOES_NOT_EXIST;
        }
    }
}

package command;

import java.util.Stack;

import duke.DukeException;
import task.TaskList;

/**
 * Command deals with executing a user command.
 */
public abstract class Command {
    /* The stack of previous commands (excluding List, Find, Exit, Undo) executed. */
    private static Stack<Command> prevCommands = new Stack<>();

    /**
     * Adds previous command to the stack of previous commands executed.
     * @param prevCommand The previous command to add.
     */
    public static void addPrevCommand(Command prevCommand) {
        prevCommands.push(prevCommand);
    }

    /**
     * Undoes whatever the latest command does to the list of tasks.
     * @param tasks The list of tasks affected by the latest command.
     * @return The return status of the result from undoing the previous command
     *     in the form of a text message.
     * @throws DukeException If undoing the previous command throws a DukeException.
     */
    static String undoPrevCommand(TaskList tasks) throws DukeException {
        if (Command.prevCommands.size() == 0) {
            return "You are already at the oldest change...";
        }
        Command prevCommand = prevCommands.pop();
        return prevCommand.undo(tasks);
    }

    /**
     * Executes the command which might cause changes to the existing task list.
     * @param tasks The existing task list.
     * @return The return status of the result from executing this command in the form of a text message.
     * @throws DukeException If there is an error encountered during the execution of the command.
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Undoes whatever this command does to the list of tasks.
     * @param tasks The list of tasks affected by the latest command.
     * @return The return status of the result from undoing this command in the form of a text message.
     * @throws DukeException If undoing this command throws a DukeException.
     */
    abstract String undo(TaskList tasks) throws DukeException;

    /**
     * Determines if the current command is an exit command.
     * @return True if the current command is an exit command; false if otherwise.
     */
    public abstract boolean isExit();
}

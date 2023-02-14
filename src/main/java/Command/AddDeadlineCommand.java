package duke.command;

import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command by user to Duke to add a Deadline task
 */
public class AddDeadlineCommand extends Command {
    private String name;
    private String by;

    /**
     * Initialises new instance of AddDeadlineCommand.
     *
     * @param name The name of the task.
     * @param by The dateline for the task in a String.
     */
    public AddDeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false Add Deadline task is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the Deadline task and the deadline corresponding to the task, to user's list of Tasks. Prints a message
     * indicating to user that Deadline task was successfully added.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     */
    public String execute(TaskList tasks, Storage storage) {
        return Ui.addTaskResponse(tasks.addDeadline(name, by), tasks);
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

import java.time.format.DateTimeParseException;

/**
 * Represents a command by user to Duke to add a Deadline task
 *
 * @ Karen
 */
public class AddDeadlineCommand extends Command{
    private String name;
    private String by;

    public AddDeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false. Add Deadline task is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the Deadline task and the deadline corresponding to the task, to user's list of Tasks. Prints a message
     * indicating to user that Deadline task was successfully added.
     *
     * @param tasks. A TaskList containing the set of task the user has.
     * @param ui. An Ui which allows for interaction between Duke and user.
     * @param storage. A Storage enabling Duke to store memory.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.addTaskResponse(tasks.addDeadline(name, by), tasks);
        } catch (DateTimeParseException e1) {
            ui.invalidTiming();
        } catch (IllegalArgumentException e2) {
            ui.incompleteCommandErrorMessage();
        } catch (ArrayIndexOutOfBoundsException e3) {
            ui.incompleteCommandErrorMessage();
        }
    }
}

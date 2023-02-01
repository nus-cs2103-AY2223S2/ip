package duke.command;

import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command by user to Duke to add an Event task
 *
 * @author Karen
 */
public class AddEventCommand extends Command {

    private String name;
    private String from;
    private String to;

    /**
     * Initialises new instance of AddEventCommand.
     *
     * @param name The name of the task.
     * @param from The starting date of the task in a String.
     * @param to The ending date of the task in a String.
     */
    public AddEventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
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
     * Adds the Event task and the start and end timing corresponding to the task, to user's list of Tasks.
     * Prints a message indicating to user that Event task was successfully added.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param ui An Ui which allows for interaction between Duke and user.
     * @param storage A Storage enabling Duke to store memory.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.addTaskResponse(tasks.addEvent(name, from, to), tasks);
        } catch (DateTimeParseException e1) {
            ui.invalidTiming();
        } catch (IllegalArgumentException e2) {
            ui.incompleteCommandErrorMessage();
        } catch (ArrayIndexOutOfBoundsException e3) {
            ui.incompleteCommandErrorMessage();
        }
    }

}

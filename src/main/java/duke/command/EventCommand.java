package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;
import duke.task.Event;
import duke.task.Task;

/**
 * Command: Creates a Event Task
 */
public class EventCommand extends Command {
    private String title;
    private String to;
    private String from;

    /**
     * Creates an event command with the given title and to and from
     *
     * @param title of the Task that that is being created
     * @param to    of the event ending date
     * @param from  of the event starting date
     * @throws DukeException
     */
    public EventCommand(String... input) throws DukeException {
        try {
            this.title = input[0];
            this.to = input[1];
            this.from = input[2];
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Views.MISSING_ARGS_ERR_STRING);
        }
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(this.title, this.to, this.from);
        tasks.add(newTask);
        ui.showAdd(newTask);
        storage.save(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(this.title, this.to, this.from);
        tasks.add(newTask);
        storage.save(tasks);
        return ui.stringAdd(newTask);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}

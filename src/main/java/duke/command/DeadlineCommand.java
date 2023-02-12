package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Command: creates a deadline Task
 */
public class DeadlineCommand extends Command {
    private String title;
    private String deadline;

    /**
     * Creates a deadline command given a title and deadline String
     *
     * @param title    of the Task that that is being created
     * @param deadline String input from the user
     * @throws DukeException
     */
    public DeadlineCommand(String... input) throws DukeException {
        try {
            this.title = input[0];
            this.deadline = input[1];
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
        Task newTask = new Deadline(this.title, this.deadline);
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
        Task newTask = new Deadline(this.title, this.deadline);
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

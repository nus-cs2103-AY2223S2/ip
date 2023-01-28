package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.TaskList;

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
     */
    public EventCommand(String title, String to, String from) {
        this.title = title;
        this.to = to;
        this.from = from;
    }

    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(this.title, this.to, this.from);
        tasks.add(newTask);
        ui.showAdd(newTask);
        storage.save(tasks);
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
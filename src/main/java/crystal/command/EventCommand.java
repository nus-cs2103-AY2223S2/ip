package crystal.command;

import crystal.CrystalException;
import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Event;

/**
 * Represents the event command when the user enters "event".
 *
 */
public class EventCommand extends Command{
    public String from;
    public String to;
    public String description;

    /**
     * Constructor for EventCommand class.
     *
     * @param description description of the task
     * @param from start date of task
     * @param to end date of task
     *
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command to print the event message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui,Storage storage) throws CrystalException {
        Event event = new Event(description, from, to);
        tasks.add(event);
        storage.saveFile(tasks);
        return ui.printEvent(tasks, event);

    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}

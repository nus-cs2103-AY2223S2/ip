package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Events;

import java.io.IOException;

/**
 * A command for adding an event to the task list.
 */
public class EventCommand extends Command {
    private final Events event;
    public EventCommand(Events event) {
        this.event = event;
    }

    /**
     * Adds an event to the task list, before saving it.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.event);
        String message = "Beep boop. I've added this task:\n" + this.event
                + String.format("\nNow you have %s tasks in the list.", tasks.size());
        ui.display(message);
        try {
            storage.dumpFile(tasks);
        } catch (Exception err) {
            throw new DukeException("BEEP BOOP. Error while saving file!");
        }
        return message;

    }
}

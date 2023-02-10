package duke.commands;

import duke.exceptions.IOException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Command to create a new Deadline.
 *
 * @author Samarth Verma
 */
public class CreateDeadline extends Command {

    private String description;
    private String by;

    /**
     * Creates a new CreateDeadline command.
     *
     * @param description The description of the deadline.
     * @param by          The deadline.
     */
    public CreateDeadline(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws IOException {
        Deadline deadline = new Deadline(list.nextId(), description, by);
        list.add(deadline);
        ui.showMessage("Got it. I've added this task: " + deadline);

        storage.save(list);
    }
}

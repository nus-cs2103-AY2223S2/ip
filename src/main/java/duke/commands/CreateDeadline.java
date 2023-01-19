package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class CreateDeadline extends Command {
    private String description;
    private String by;

    public CreateDeadline(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList list, UserInterface ui) {
        Deadline deadline = new Deadline(list.nextId(), description, by);
        list.add(deadline);
        ui.showMessage("Got it. I've added this task: " + deadline);
    }

}

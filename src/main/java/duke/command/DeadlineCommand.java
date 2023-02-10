package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * The DeadlineCommand class encapsulates the variables and methods related to Deadline commands.
 */
public class DeadlineCommand extends Command {
    public static final String DEADLINE_COMMAND = "deadline";
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        super(DEADLINE_COMMAND);
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList lst, Ui ui) throws DukeException {
        lst.addTask(this.deadline);
        ui.showDeadline(this.deadline, lst.getSize());
    }
}

package duke.command;

import duke.task.Deadline;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

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

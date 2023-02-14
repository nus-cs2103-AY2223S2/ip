package luffy.command;

import luffy.storage.TaskList;
import luffy.ui.Ui;
import luffy.exception.LuffyException;
import luffy.task.Deadline;

/**
 * The DeadlineCommand class encapsulates the variables and methods related to Deadline commands.
 */
public class DeadlineCommand extends Command {
    private static final String DEADLINE_COMMAND = "deadline";
    private final Deadline deadline;

    /**
     * Constructor creates an instance of DeadlineCommand.
     * @param deadline An instance of Deadline.
     */
    public DeadlineCommand(Deadline deadline) {
        super(DEADLINE_COMMAND);
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList lst, Ui ui) throws LuffyException {
        lst.addTask(this.deadline);
        return ui.showDeadline(this.deadline, lst.getSize());
    }
}

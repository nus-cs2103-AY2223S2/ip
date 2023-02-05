package duke.commands;

import java.time.LocalDateTime;

import duke.taskers.Deadline;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Command for adding deadlines.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private final Deadline deadline;

    /**
     * Constructor.
     *
     * @param desc Description of task.
     * @param isDone If the task is done or not.
     * @param endDate End date of the task.
     */
    public AddDeadlineCommand(String desc, boolean isDone, boolean isPriority, LocalDateTime endDate) {
        super();
        this.deadline = new Deadline(desc, isDone, isPriority, endDate);
    }

    /**
     * Executes the command.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addItem(this.deadline);
        storage.writeToFile(this.deadline);
        return ui.addItemResponse(this.deadline, taskList.getList());
    }

    /**
     * Checks if this command calls to exit.
     *
     * @return If the command is exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }


}

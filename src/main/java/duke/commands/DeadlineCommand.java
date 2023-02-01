package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.time.LocalDate;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    protected String desc;
    protected LocalDate by;

    /**
     * A constructor to initialize a deadline command.
     *
     * @param desc The description of the deadline object.
     * @param by The deadline of the deadline object.
     */
    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        Task t = new Deadline(by, desc);
        taskList.addTask(t);
        storage.save(taskList);
        return ui.printTaskAdded(t, taskList);
    }

}

package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    protected int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.unmarkTask(taskNumber);
            storage.save(taskList);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

}

package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.time.LocalDate;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    protected int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.markTask(taskNumber);
            storage.save(taskList);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

}

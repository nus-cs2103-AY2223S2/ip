package duke.command;

import duke.exception.DukeException;

import duke.tasklist.TaskList;

/**
 * Represents a command that deletes a Task from the list of Tasks.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;

    }

    @Override
    public void execute(TaskList taskList) throws DukeException {
        taskList.deleteTask(this.taskNumber);
    }

}

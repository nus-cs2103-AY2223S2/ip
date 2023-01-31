package duke.command;

import duke.exception.DukeException;

import duke.tasklist.TaskList;

/**
 * Represents a command that marks a Task in the list of Tasks, as done.
 */
public class MarkCommand extends Command {

    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;

    }

    @Override
    public void execute(TaskList taskList) throws DukeException {
        taskList.markTask(this.taskNumber);
    }

}

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

    /**
     * Executes the command by marking a particular Task in the list of Tasks as done.
     *
     * @param taskList The TaskList object that manages the list of Tasks.
     * @return The String response of the chatbot.
     * @throws DukeException If the task number given is not valid.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        assert taskList != null;
        return taskList.markTask(this.taskNumber);
    }

}

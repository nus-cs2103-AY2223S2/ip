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

    /**
     * Executes the command by deleting a Task from the list of Tasks.
     *
     * @param taskList The TaskList object that manages the list of Tasks.
     * @return The String response of the chatbot.
     * @throws DukeException If the task number given is not valid.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        assert taskList != null;

        return taskList.deleteTask(this.taskNumber);
    }

}

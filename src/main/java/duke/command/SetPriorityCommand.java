package duke.command;

import duke.exception.DukeException;

import duke.task.Priority;

import duke.tasklist.TaskList;

public class SetPriorityCommand extends Command {

    private final int taskNumber;
    private final Priority priority;

    public SetPriorityCommand(int taskNumber, Priority priority) {
        super(false);

        assert priority != null;

        this.taskNumber = taskNumber;
        this.priority = priority;

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
        return taskList.setTaskPriority(this.taskNumber, this.priority);
    }
}

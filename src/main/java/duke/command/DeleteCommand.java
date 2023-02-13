package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;
import duke.Task;
import duke.TaskList;

/**
 * Command that when executed deletes the given task from the task list.
 */
public class DeleteCommand extends Command {
    public final TaskList taskList;
    public final Integer taskIdx;

    /**
     * Constructs a DeleteCommand with the given arguments.
     *
     * @param taskIdx
     * @param taskList
     */
    public DeleteCommand(Integer taskIdx, TaskList taskList) {
        this.taskIdx = taskIdx;
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        assert taskIdx != null;
        assert taskList != null;
        Task task = taskList.remove(taskIdx);
        return new DukeResponse(MessageGenerator.genDeleteTaskMsg(task.toString()));
    }
}

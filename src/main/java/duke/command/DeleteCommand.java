package duke.command;

import duke.DukeResponse;
import duke.TaskList;

public class DeleteCommand extends Command {
    public final TaskList taskList;
    public final Integer taskIdx;

    public DeleteCommand(Integer taskIdx, TaskList taskList) {
        this.taskIdx = taskIdx;
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        taskList.remove(taskIdx);
        return new DukeResponse("deleted task");
    }
}

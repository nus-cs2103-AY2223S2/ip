package duke.command;

import duke.TaskList;

public class DeleteCommand extends Command {
    public final TaskList taskList;
    public final Integer taskIdx;

    public DeleteCommand(Integer taskIdx, TaskList taskList) {
        this.taskIdx = taskIdx;
        this.taskList = taskList;
    }


    @Override
    public String execute() {
        taskList.remove(taskIdx);
        return "deleted task";
    }
}

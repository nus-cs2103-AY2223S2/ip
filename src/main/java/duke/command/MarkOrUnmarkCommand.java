package duke.command;

import duke.Task;
import duke.TaskList;
import duke.exception.DukeException;


public class MarkOrUnmarkCommand extends Command {
    private final Boolean isMark;
    private final TaskList taskList;
    private final Integer taskIdx;

    public MarkOrUnmarkCommand(int taskIdx, TaskList taskList, boolean isMark) {
        this.taskIdx = taskIdx;
        this.taskList = taskList;
        this.isMark = isMark;
    }


    @Override
    public String execute() {
        Task task = taskList.get(taskIdx);

        if (isMark) {
            task.mark();
            return "Placeholder: marked the task";
        } else {
            task.unmark();
            return "Placeholder: unmarked the task";
        }
    }

}

package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;
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
    public DukeResponse execute() {
        assert taskList != null;
        assert taskIdx != null;
        assert isMark != null;

        Task task = taskList.get(taskIdx);

        if (isMark) {
            task.mark();
            return new DukeResponse(MessageGenerator.genMarkorUnmarkTaskMsg(task.toString(), true));
        } else {
            task.unmark();
            return new DukeResponse(MessageGenerator.genMarkorUnmarkTaskMsg(task.toString(), false));
        }
    }

}

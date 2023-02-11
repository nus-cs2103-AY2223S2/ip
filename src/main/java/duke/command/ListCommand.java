package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;
import duke.TaskList;

public class ListCommand extends Command{

    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        assert taskList != null;
        return new DukeResponse(MessageGenerator.genShowTasksMsg(taskList.toString()));
    }
}

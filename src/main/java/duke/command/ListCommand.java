package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;
import duke.TaskList;

/**
 * Represents a command that when executed returns a list of all tasks.
 */
public class ListCommand extends Command {

    private final TaskList taskList;

    /**
     * Constructs a ListCommand with the given task list.
     *
     * @param taskList the task list to be returned.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        assert taskList != null;
        return new DukeResponse(MessageGenerator.genShowTasksMsg(taskList.toString()));
    }
}

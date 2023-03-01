package duke.command;

import duke.DukeResponse;
import duke.TaskList;
import duke.ToDo;

/**
 * Represents a command that when executed adds a ToDo task to the given task list.
 */
public class ToDoCommand extends Command {
    private final String taskDesc;
    private final TaskList taskList;

    /**
     * Constructs a ToDo task with the given arguments.
     *
     * @param taskDesc
     * @param taskList
     */
    public ToDoCommand(String taskDesc, TaskList taskList) {
        this.taskDesc = taskDesc;
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        assert taskDesc != null;
        assert taskList != null;

        ToDo todo = new ToDo(taskDesc);
        taskList.add(todo);
        return new DukeResponse("added todo");
    }
}

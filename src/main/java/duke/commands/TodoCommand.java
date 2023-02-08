package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.TextUi;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    protected String desc;

    /**
     * A constructor to initialize a todo command.
     *
     * @param desc The description of the todo task.
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        Task t = new Todo(desc);
        System.out.println(t.getPrioritySign());
        taskList.addTask(t);
        storage.save(taskList);
        return ui.printTaskAdded(t, taskList);
    }

}

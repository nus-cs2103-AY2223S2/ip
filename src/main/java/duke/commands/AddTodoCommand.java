package duke.commands;

import duke.taskers.Todo;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Command for adding todos.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private Todo todo;

    /**
     * Constructor.
     *
     * @param desc Description of todo.
     * @param isDone If task is done or not.
     */
    public AddTodoCommand(String desc, boolean isDone) {
        super();
        this.todo = new Todo(desc, isDone);
    }

    /**
     * Executes the task.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addItem(this.todo);
        storage.writeToFile(this.todo);
        return ui.addItemResponse(this.todo, taskList.getList());

    }

    /**
     * Checks if command exits.
     *
     * @return If command exits or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}

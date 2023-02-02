package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that adds a Todo Task to the list.
 */
public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Execute adding Todo Task to list and storing it in Storage.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.reply(taskList.addTodo(description));
        storage.saveState(taskList);
    }
}

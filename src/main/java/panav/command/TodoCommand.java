package panav.command;

import panav.exception.ToDoDescriptionException;
import panav.storage.Storage;
import panav.task.Task;
import panav.task.TaskList;
import panav.task.ToDo;
import panav.ui.Ui;

/**
 * Class to encapsulate a 'todo' command, extends from Command.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private String todoMessage;

    public TodoCommand(String todoMessage) {
        this.todoMessage = todoMessage;
    }

    /**
     * Specifies the behaviour of 'todo' command when called to execute. Creates a new todo
     * with the provided information and adds it to the list of tasks.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task curr = new ToDo(this.todoMessage);
            tasks.addTask(curr);
            return ui.showAddTaskMessage(tasks, curr);
        } catch (ToDoDescriptionException e) {
            return e.getMessage();
        }

    }

    @Override
    public String toString() {
        return TodoCommand.COMMAND_WORD;
    }
}

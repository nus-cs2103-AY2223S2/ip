package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.task.Todo;

/**
 * ToDoCommand that has description.
 */
public class ToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    private String desc;

    /**
     * Constructor for ToDoCommand.
     * @param desc
     */
    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes adding of todo task to task list.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTask = new Todo(desc);
        tasks.addTask(newTask);
    }

}

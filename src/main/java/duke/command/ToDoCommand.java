package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.task.Todo;

public class ToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    private String desc;

    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTask = new Todo(desc);
        tasks.addTask(newTask);
    }

}

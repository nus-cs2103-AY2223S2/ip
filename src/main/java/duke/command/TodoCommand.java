package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
public class TodoCommand extends Command {
    private final String title;

    public TodoCommand(String title) {
        this.title = title;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTodo = tasks.addTodo(title);
        return ui.printAddTask(newTodo, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

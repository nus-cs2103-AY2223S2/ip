package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.gui.GuiText;
import duke.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks.createTaskListString());
        return guiText.showAddTask(task, tasks);
    }

}

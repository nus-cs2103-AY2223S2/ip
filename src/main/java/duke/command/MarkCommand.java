package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.gui.GuiText;
import duke.task.Task;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(index);
        storage.save(tasks.createTaskListString());
        return guiText.showMarkTask(task);
    }

}

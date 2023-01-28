package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.gui.GuiText;
import duke.gui.MainWindow;
import duke.gui.SpriteEmotion;
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
        MainWindow.changeSpriteExpression(SpriteEmotion.HAPPY);
        return guiText.showMarkTask(task);
    }

}

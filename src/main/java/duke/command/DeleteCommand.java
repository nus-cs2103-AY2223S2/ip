package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.gui.GuiText;
import duke.gui.MainWindow;
import duke.gui.SpriteEmotion;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws DukeException {
        Task task = tasks.processTaskAtIndex(CommandType.DELETE, this.index);
        storage.save(tasks.createTaskListString());
        MainWindow.changeSpriteExpression(SpriteEmotion.NEUTRAL);
        return guiText.showDeleteTask(task, tasks);
    }

}

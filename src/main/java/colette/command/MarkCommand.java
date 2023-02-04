package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.exception.ColetteException;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;
import colette.task.Task;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws ColetteException {
        Task task = tasks.processTaskAtIndex(CommandType.MARK, index);
        storage.save(tasks.createTaskListString());
        MainWindow.changeSpriteExpression(SpriteEmotion.HAPPY);
        return guiText.showMarkTask(task);
    }

}

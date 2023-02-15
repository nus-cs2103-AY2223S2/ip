package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.exception.ColetteException;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;
import colette.task.Task;

/** Class that represents a user command to mark a task */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a MarkCommand object
     * that marks the task in the list
     * at the given index.
     *
     * @param index Index of the list to mark the task at.
     */
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

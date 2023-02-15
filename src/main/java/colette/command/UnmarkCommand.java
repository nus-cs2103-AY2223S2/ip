package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.exception.ColetteException;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;
import colette.task.Task;

/** Class that represents a user command to unmark a task */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an UnmarkCommand object
     * that unmarks the task in the list
     * at the given index.
     *
     * @param index Index of the list to unmark the task at.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws ColetteException {
        Task task = tasks.processTaskAtIndex(CommandType.UNMARK, index);
        storage.save(tasks.createTaskListString());
        MainWindow.changeSpriteExpression(SpriteEmotion.SURPRISED);
        return guiText.showUnmarkTask(task);
    }

}

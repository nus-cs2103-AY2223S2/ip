package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.exception.ColetteException;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;
import colette.task.Task;

/** Class that represents a user command to delete a task */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand object
     * that deletes the task in the list
     * at the given index.
     *
     * @param index Index of the list to delete the task from.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws ColetteException {
        Task task = tasks.processTaskAtIndex(CommandType.DELETE, this.index);
        storage.save(tasks.createTaskListString());
        MainWindow.changeSpriteExpression(SpriteEmotion.SURPRISED);
        return guiText.showDeleteTask(task, tasks);
    }

}

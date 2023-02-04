package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.exception.ColetteException;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;
import colette.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) throws ColetteException {
        tasks.addTask(task);
        storage.save(tasks.createTaskListString());
        MainWindow.changeSpriteExpression(SpriteEmotion.HAPPY);
        return guiText.showAddTask(task, tasks);
    }

}

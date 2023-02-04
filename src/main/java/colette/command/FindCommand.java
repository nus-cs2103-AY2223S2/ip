package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;

/** Class that represents a user command to find tasks */
public class FindCommand extends Command {

    private String[] keywords;

    /**
     * Constructs a FindCommand object
     * that finds all tasks that match any of the
     * given keywords.
     *
     * @param keywords Keywords to match tasks to.
     */
    public FindCommand(String ... keywords) {
        super(false);
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        TaskList filteredTaskList = tasks.findTasksByKeyword(this.keywords);
        SpriteEmotion spriteEmotion = filteredTaskList.isEmpty() ? SpriteEmotion.NEUTRAL : SpriteEmotion.HAPPY;
        MainWindow.changeSpriteExpression(spriteEmotion);
        return guiText.showFind(filteredTaskList);
    }

}

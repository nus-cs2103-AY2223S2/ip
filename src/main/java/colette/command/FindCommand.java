package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;

public class FindCommand extends Command {

    private String[] keywords;

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

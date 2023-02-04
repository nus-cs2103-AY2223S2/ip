package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        MainWindow.changeSpriteExpression(SpriteEmotion.NEUTRAL);
        return guiText.showList(tasks);
    }

}

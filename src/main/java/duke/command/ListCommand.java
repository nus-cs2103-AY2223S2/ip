package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;
import duke.gui.MainWindow;
import duke.gui.SpriteEmotion;

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

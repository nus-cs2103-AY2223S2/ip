package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;
import duke.gui.MainWindow;
import duke.gui.SpriteEmotion;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList task, GuiText guiText, Storage storage) {
        MainWindow.changeSpriteExpression(SpriteEmotion.NEUTRAL);
        return guiText.showGoodbye();
    }

}

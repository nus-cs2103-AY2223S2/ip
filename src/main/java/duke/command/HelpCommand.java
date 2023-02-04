package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;
import duke.gui.HelpWindow;
import duke.gui.MainWindow;
import duke.gui.SpriteEmotion;

public class HelpCommand extends Command {
    
    public HelpCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        boolean isHelpWindowOpen = HelpWindow.isOpen();
        SpriteEmotion spriteEmotion = !isHelpWindowOpen ? SpriteEmotion.HAPPY : SpriteEmotion.SURPRISED;
        MainWindow.changeSpriteExpression(spriteEmotion);
        HelpWindow.launchHelpWindow();
        return GuiText.showHelp(isHelpWindowOpen);
    }

}
